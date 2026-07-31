import os
os.environ["JAVA_HOME"] = "/opt/java8"
os.environ["PATH"] = os.environ["JAVA_HOME"] + "/bin:" + os.environ["PATH"]

from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("MostrarTablasBancoUnion") \
    .master("local[*]") \
    .getOrCreate()

HDFS_BASE = "hdfs://namenode:8020/datalake/raw/banco_union"

# Definición de columnas según 01_mysql_schema_origen.sql
tablas = {
    "servidores": ["id_servidor", "nombre", "direccion_ip", "sistema_operativo", "ubicacion", "estado"],
    "canales": ["id_canal", "tipo_canal", "ubicacion", "disponibilidad", "numero_errores"],
    "metricas_recursos": ["id_metrica", "servidor_id", "fecha", "cpu_uso", "ram_uso", "disco_uso"],
    "logs_sistema": ["id_log", "fecha", "evento", "nivel_severidad", "servidor_id"],
    "transacciones": ["id_transaccion", "fecha", "canal_id", "monto", "estado"],
}

for nombre_tabla, columnas in tablas.items():
    print(f"\n{'='*60}")
    print(f"TABLA: {nombre_tabla}")
    print(f"{'='*60}")

    ruta = f"{HDFS_BASE}/{nombre_tabla}"
    df = spark.read.csv(ruta, header=False, inferSchema=True).toDF(*columnas)

    print("--- Esquema ---")
    df.printSchema()

    print("--- Primeras 5 filas ---")
    df.show(5, truncate=False)

    print(f"--- Total de registros: {df.count()} ---")

spark.stop()
