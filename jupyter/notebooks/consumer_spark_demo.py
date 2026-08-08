from pyspark.sql import SparkSession
from pyspark.sql.functions import col, from_json
from pyspark.sql.types import (
    StructType, StructField, StringType, IntegerType, DoubleType, ArrayType
)

spark = (
    SparkSession.builder
    .appName("ActividadKafkaStreamingDemo")
    .master("spark://spark-master:7077")
    .config(
        "spark.jars.packages",
        "org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.0",
    )
    .getOrCreate()
)
spark.sparkContext.setLogLevel("WARN")

item_schema = StructType([
    StructField("product_id", IntegerType()),
    StructField("product_name", StringType()),
    StructField("category_id", IntegerType()),
    StructField("quantity", IntegerType()),
    StructField("unit_price", DoubleType()),
    StructField("subtotal", DoubleType()),
])

evento_schema = StructType([
    StructField("event_id", StringType()),
    StructField("event_timestamp", StringType()),
    StructField("order_id", IntegerType()),
    StructField("customer_id", IntegerType()),
    StructField("customer_fname", StringType()),
    StructField("customer_lname", StringType()),
    StructField("customer_city", StringType()),
    StructField("customer_state", StringType()),
    StructField("order_status", StringType()),
    StructField("channel", StringType()),
    StructField("payment_method", StringType()),
    StructField("store_id", IntegerType()),
    StructField("items", ArrayType(item_schema)),
    StructField("order_total", DoubleType()),
])

df_raw = (
    spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "kafka:9092")
    .option("subscribe", "retail_orders_stream")
    .option("startingOffsets", "earliest")
    .load()
)

df_eventos = (
    df_raw
    .selectExpr("CAST(value AS STRING) AS json_str")
    .select(from_json(col("json_str"), evento_schema).alias("evento"))
    .select("evento.*")
)

ventas_por_canal = (
    df_eventos
    .groupBy("channel")
    .sum("order_total")
    .withColumnRenamed("sum(order_total)", "ventas_totales")
)

query_canal = (
    ventas_por_canal.writeStream
    .outputMode("complete")
    .format("console")
    .option("truncate", "false")
    .queryName("ventas_por_canal")
    .trigger(processingTime="5 seconds")
    .start()
)

ventas_por_tienda = (
    df_eventos
    .groupBy("store_id")
    .sum("order_total")
    .withColumnRenamed("sum(order_total)", "ventas_totales")
)

query_tienda = (
    ventas_por_tienda.writeStream
    .outputMode("complete")
    .format("console")
    .option("truncate", "false")
    .queryName("ventas_por_tienda")
    .trigger(processingTime="5 seconds")
    .start()
)

print(">> Streaming iniciado. Revisa http://localhost:8080 y http://localhost:4040")
print(">> Corriendo consultas 'ventas_por_canal' y 'ventas_por_tienda' cada 5s...")

spark.streams.awaitAnyTermination()
