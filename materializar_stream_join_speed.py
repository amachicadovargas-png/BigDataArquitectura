"""
Materialización del proceso STREAM con JOIN (orders + order_items) -> capa SPEED
BigDataArquitectura - Modelo Lambda

Lee los tópicos Kafka orders_topic y order_items_topic (estructuras nativas
de retail_db), aplica watermarks de 2 minutos, realiza el JOIN en streaming
y materializa el resultado como archivos parquet en HDFS (capa SPEED),
con checkpoint para tolerancia a fallos.

Ejecutar dentro del contenedor jupyter / spark-master del stack
docker-compose-actualizado.yml.
"""

from pyspark.sql import SparkSession
from pyspark.sql.functions import from_json, col, current_timestamp
from pyspark.sql.types import StructType, StructField, IntegerType, StringType, FloatType, TimestampType

spark = SparkSession.builder.appName("MaterializarStreamJoinSpeed").getOrCreate()
spark.sparkContext.setLogLevel("WARN")

KAFKA_BOOTSTRAP = "kafka:9092"
SPEED_PATH = "hdfs://namenode:8020/lambda/speed"
CHECKPOINT_PATH = "hdfs://namenode:8020/lambda/checkpoint_speed"

# --- Esquemas nativos de retail_db (según emulador_datos.py / mysql_kafka_producer.py) ---
orders_schema = StructType([
    StructField("order_id", IntegerType()),
    StructField("order_date", TimestampType()),
    StructField("order_customer_id", IntegerType()),
    StructField("order_status", StringType()),
])

order_items_schema = StructType([
    StructField("order_item_id", IntegerType()),
    StructField("order_item_order_id", IntegerType()),
    StructField("order_item_product_id", IntegerType()),
    StructField("order_item_quantity", IntegerType()),
    StructField("order_item_subtotal", FloatType()),
    StructField("order_item_product_price", FloatType()),
])

# --- Lectura de los tópicos Kafka ---
orders_raw = (
    spark.readStream.format("kafka")
    .option("kafka.bootstrap.servers", KAFKA_BOOTSTRAP)
    .option("subscribe", "orders_topic")
    .option("startingOffsets", "latest")
    .load()
)

order_items_raw = (
    spark.readStream.format("kafka")
    .option("kafka.bootstrap.servers", KAFKA_BOOTSTRAP)
    .option("subscribe", "order_items_topic")
    .option("startingOffsets", "latest")
    .load()
)

orders_stream = (
    orders_raw.selectExpr("CAST(value AS STRING) AS json")
    .select(from_json(col("json"), orders_schema).alias("data"))
    .select("data.*")
)

items_stream = (
    order_items_raw.selectExpr("CAST(value AS STRING) AS json")
    .select(from_json(col("json"), order_items_schema).alias("data"))
    .select("data.*")
    .withColumn("event_time", current_timestamp())
)

# --- Watermarks de 2 minutos (tal como exige la capa Stream del entregable) ---
orders_stream_watermark = orders_stream.withWatermark("order_date", "2 minutes")
items_stream_watermark = items_stream.withWatermark("event_time", "2 minutes")

# --- JOIN en streaming ---
ventas = orders_stream_watermark.join(
    items_stream_watermark,
    orders_stream_watermark.order_id == items_stream_watermark.order_item_order_id,
    "inner",
).select(
    "order_id",
    "order_date",
    "order_status",
    "order_item_product_id",
    "order_item_quantity",
    "order_item_subtotal",
)

# --- Materialización a archivo (parquet) en la capa SPEED, cada 2 minutos ---
streaming_query = (
    ventas.writeStream
    .format("parquet")
    .outputMode("append")
    .option("path", SPEED_PATH)
    .option("checkpointLocation", CHECKPOINT_PATH)
    .trigger(processingTime="2 minutes")
    .start()
)

streaming_query.awaitTermination()
