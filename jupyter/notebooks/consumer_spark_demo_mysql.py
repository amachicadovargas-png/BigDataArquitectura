from pyspark.sql import SparkSession
from pyspark.sql.functions import col, from_json, lit, current_timestamp
from pyspark.sql.types import (
    StructType, StructField, StringType, IntegerType, DoubleType, ArrayType
)

spark = (
    SparkSession.builder
    .appName("ActividadKafkaStreamingDemoMySQL")
    .master("spark://spark-master:7077")
    .config(
        "spark.jars.packages",
        "org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1,mysql:mysql-connector-j:8.3.0",
    )
    .getOrCreate()
)
spark.sparkContext.setLogLevel("WARN")

JDBC_URL = "jdbc:mysql://mysql:3306/retail_db?useSSL=false&allowPublicKeyRetrieval=true"
JDBC_PROPS = {
    "user": "root",
    "password": "root",
    "driver": "com.mysql.cj.jdbc.Driver",
}

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

def escribir_ventas_por_canal(batch_df, batch_id):
    agregado = (
        batch_df.groupBy("channel")
        .sum("order_total")
        .withColumnRenamed("sum(order_total)", "ventas_totales")
        .withColumn("actualizado_en", current_timestamp())
        .withColumn("batch_id", lit(batch_id))
    )
    agregado.write.jdbc(
        url=JDBC_URL, table="ventas_por_canal", mode="overwrite", properties=JDBC_PROPS
    )
    print(f">> [batch {batch_id}] ventas_por_canal actualizada en MySQL ({agregado.count()} filas)")


def escribir_ventas_por_tienda(batch_df, batch_id):
    agregado = (
        batch_df.groupBy("store_id")
        .sum("order_total")
        .withColumnRenamed("sum(order_total)", "ventas_totales")
        .withColumn("actualizado_en", current_timestamp())
        .withColumn("batch_id", lit(batch_id))
    )
    agregado.write.jdbc(
        url=JDBC_URL, table="ventas_por_tienda", mode="overwrite", properties=JDBC_PROPS
    )
    print(f">> [batch {batch_id}] ventas_por_tienda actualizada en MySQL ({agregado.count()} filas)")


query_canal = (
    df_eventos.writeStream
    .foreachBatch(escribir_ventas_por_canal)
    .outputMode("update")
    .queryName("ventas_por_canal_mysql")
    .trigger(processingTime="5 seconds")
    .start()
)

query_tienda = (
    df_eventos.writeStream
    .foreachBatch(escribir_ventas_por_tienda)
    .outputMode("update")
    .queryName("ventas_por_tienda_mysql")
    .trigger(processingTime="5 seconds")
    .start()
)

print(">> Streaming iniciado. Persistiendo en MySQL cada 5s.")
print(">> Revisa en Adminer: SELECT * FROM ventas_por_canal; / SELECT * FROM ventas_por_tienda;")

spark.streams.awaitAnyTermination()
