from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("BancoUnion") \
    .master("spark://spark-master:7077") \
    .getOrCreate()

df_transacciones = spark.read.csv(
    "/user/app/source/transacciones.csv",
    header=True,
    inferSchema=True,
    sep=","
)

df_transacciones.show(10)
df_transacciones.printSchema()
print("Total de registros:", df_transacciones.count())

spark.stop()
