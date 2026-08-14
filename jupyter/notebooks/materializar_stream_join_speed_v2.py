"""
Materialización del proceso STREAM con JOIN (orders + order_items) -> capa SPEED
BigDataArquitectura - Modelo Lambda
VERSIÓN 2: JOIN dentro de foreachBatch (orders en streaming desde Kafka +
order_items vía JDBC a MySQL), evitando el state store del join stream-stream
que resultó inestable en este entorno (checkpoint con archivos .delta/.changelog
que HDFS/filesystem local no lograba leer de forma confiable justo después de
escribirlos).

El checkpoint de esta versión solo guarda offsets de Kafka (un archivo simple),
no estado del join, lo que elimina el problema de raíz.

Ejecutar con:
docker exec -it jupyter spark-submit \\
  --master local[1] \\
  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 \\
  /home/jovyan/work/materializar_stream_join_speed_v2.py
"""

from pyspark.sql import SparkSession
from pyspark.sql.functions import from_json, col
from pyspark.sql.types import StructType, StructField, IntegerType, StringType, TimestampType

spark = SparkSession.builder.appName("MaterializarStreamJoinSpeedV2").getOrCreate()
spark.conf.set("spark.sql.shuffle.partitions", "4")
spark.sparkContext.setLogLevel("WARN")

KAFKA_BOOTSTRAP = "kafka:9092"
SPEED_PATH = "hdfs://namenode:8020/lambda/speed"
CHECKPOINT_PATH = "hdfs://namenode:8020/lambda/checkpoint_speed_v2"

# --- Conexión JDBC a MySQL (para leer order_items en cada micro-batch) ---
MYSQL_URL = "jdbc:mysql://172.27.1.15:3306/retail_db"
MYSQL_PROPERTIES = {
    "user": "root",
    "password": "root",
    "driver": "com.mysql.cj.jdbc.Driver",
}

# --- Esquema nativo de orders (según emulador_datos.py / mysql_kafka_producer.py) ---
orders_schema = StructType([
    StructField("order_id", IntegerType()),
    StructField("order_date", TimestampType()),
    StructField("order_customer_id", IntegerType()),
    StructField("order_status", StringType()),
])

# --- Lectura del tópico Kafka orders_topic (dispara el trigger) ---
orders_raw = (
    spark.readStream.format("kafka")
    .option("kafka.bootstrap.servers", KAFKA_BOOTSTRAP)
    .option("subscribe", "orders_topic")
    .option("startingOffsets", "latest")
    .load()
)

orders_stream = (
    orders_raw.selectExpr("CAST(value AS STRING) AS json")
    .select(from_json(col("json"), orders_schema).alias("data"))
    .select("data.*")
)


def procesar_batch(orders_batch_df, batch_id):
    """
    Por cada micro-batch de orders llegado desde Kafka:
    1. Si no hay orders nuevas, no hace nada (evita lecturas JDBC innecesarias).
    2. Lee order_items completa desde MySQL vía JDBC (batch, no streaming).
    3. Hace el JOIN normal (no stream-stream) entre las orders del micro-batch
       y los order_items correspondientes.
    4. Escribe (materializa) el resultado como parquet en la capa SPEED.
    """
    if orders_batch_df.rdd.isEmpty():
        print(f"[batch {batch_id}] Sin orders nuevas, se omite.")
        return

    order_items = spark.read.jdbc(
        url=MYSQL_URL, table="order_items", properties=MYSQL_PROPERTIES
    )

    ventas = orders_batch_df.join(
        order_items,
        orders_batch_df.order_id == order_items.order_item_order_id,
        "inner",
    ).select(
        "order_id",
        "order_date",
        "order_status",
        "order_item_product_id",
        "order_item_quantity",
        "order_item_subtotal",
    )

    count = ventas.count()
    print(f"[batch {batch_id}] {count} filas materializadas en SPEED.")

    if count > 0:
        ventas.write.mode("append").parquet(SPEED_PATH)


# --- Materialización a archivo (parquet) en la capa SPEED, cada 2 minutos ---
streaming_query = (
    orders_stream.writeStream
    .foreachBatch(procesar_batch)
    .option("checkpointLocation", CHECKPOINT_PATH)
    .trigger(processingTime="2 minutes")
    .start()
)

streaming_query.awaitTermination()
