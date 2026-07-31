cat > consultas_banco.py << 'PYEOF'
import os
os.environ["JAVA_HOME"] = "/opt/java8"
os.environ["PATH"] = os.environ["JAVA_HOME"] + "/bin:" + os.environ["PATH"]

from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("ConsultasBancoUnion") \
    .master("local[*]") \
    .getOrCreate()

HDFS_BASE = "hdfs://namenode:8020/datalake/raw/banco_union"

tablas = {
    "servidores": ["id_servidor", "nombre", "direccion_ip", "sistema_operativo", "ubicacion", "estado"],
    "canales": ["id_canal", "tipo_canal", "ubicacion", "disponibilidad", "numero_errores"],
    "metricas_recursos": ["id_metrica", "servidor_id", "fecha", "cpu_uso", "ram_uso", "disco_uso"],
    "logs_sistema": ["id_log", "fecha", "evento", "nivel_severidad", "servidor_id"],
    "transacciones": ["id_transaccion", "fecha", "canal_id", "monto", "estado"],
}

for nombre_tabla, columnas in tablas.items():
    ruta = f"{HDFS_BASE}/{nombre_tabla}"
    df = spark.read.csv(ruta, header=False, inferSchema=True).toDF(*columnas)
    df.createOrReplaceTempView(nombre_tabla)

print("Vistas creadas: servidores, canales, metricas_recursos, logs_sistema, transacciones\n")

print("=== 1. Total de transacciones por estado ===")
spark.sql("""
    SELECT estado, COUNT(*) as total, ROUND(SUM(monto),2) as monto_total
    FROM transacciones
    GROUP BY estado
    ORDER BY total DESC
""").show()

print("=== 2. Top 5 canales con mas errores ===")
spark.sql("""
    SELECT id_canal, tipo_canal, ubicacion, numero_errores
    FROM canales
    ORDER BY numero_errores DESC
    LIMIT 5
""").show()

print("=== 3. Promedio de CPU/RAM/Disco por servidor ===")
spark.sql("""
    SELECT servidor_id,
           ROUND(AVG(cpu_uso),2) as cpu_prom,
           ROUND(AVG(ram_uso),2) as ram_prom,
           ROUND(AVG(disco_uso),2) as disco_prom
    FROM metricas_recursos
    GROUP BY servidor_id
    ORDER BY cpu_prom DESC
""").show()

print("=== 4. JOIN: transacciones con detalle del canal ===")
spark.sql("""
    SELECT t.id_transaccion, t.monto, t.estado, c.tipo_canal, c.ubicacion
    FROM transacciones t
    JOIN canales c ON t.canal_id = c.id_canal
    LIMIT 10
""").show()

print("=== 5. Servidores con eventos de severidad CRITICA ===")
spark.sql("""
    SELECT s.nombre, s.ubicacion, l.evento, l.fecha, l.nivel_severidad
    FROM logs_sistema l
    JOIN servidores s ON l.servidor_id = s.id_servidor
    WHERE l.nivel_severidad = 'CRITICO'
""").show(truncate=False)

print("=== 6. Tasa de fallo de transacciones por canal ===")
spark.sql("""
    SELECT c.tipo_canal, c.ubicacion,
           COUNT(*) as total_transacciones,
           SUM(CASE WHEN t.estado = 'FALLIDA' THEN 1 ELSE 0 END) as fallidas,
           ROUND(SUM(CASE WHEN t.estado = 'FALLIDA' THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) as tasa_fallo_pct
    FROM transacciones t
    JOIN canales c ON t.canal_id = c.id_canal
    GROUP BY c.tipo_canal, c.ubicacion
    ORDER BY tasa_fallo_pct DESC
""").show()

spark.stop()
PYEOF