import os
import time

os.environ["JAVA_HOME"] = "/opt/java8"
os.environ["PATH"] = os.environ["JAVA_HOME"] + "/bin:" + os.environ["PATH"]

from pyspark.sql import SparkSession

spark = SparkSession.builder \
    .appName("DashboardBancoUnion") \
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

OUTPUT_DIR = "/user/app/www"
os.makedirs(OUTPUT_DIR, exist_ok=True)

REFRESH_SECONDS = 30  # cada cuánto se actualiza la página

def generar_html():
    partes = []
    partes.append(f"""
    <html>
    <head>
        <meta http-equiv="refresh" content="{REFRESH_SECONDS}">
        <title>Dashboard Banco Union</title>
        <style>
            body {{ font-family: Arial, sans-serif; margin: 30px; background: #f4f4f4; }}
            h1 {{ color: #2c3e50; }}
            h2 {{ color: #34495e; margin-top: 40px; }}
            table {{ border-collapse: collapse; width: 100%; background: white; }}
            th, td {{ border: 1px solid #ddd; padding: 8px; font-size: 13px; }}
            th {{ background: #2c3e50; color: white; }}
            .total {{ font-weight: bold; color: #27ae60; }}
            .timestamp {{ color: #7f8c8d; font-size: 12px; }}
        </style>
    </head>
    <body>
        <h1>Dashboard - Banco Union (Datalake)</h1>
        <p class="timestamp">Ultima actualizacion: {time.strftime('%Y-%m-%d %H:%M:%S')}
        (se refresca cada {REFRESH_SECONDS}s)</p>
    """)

    for nombre_tabla, columnas in tablas.items():
        ruta = f"{HDFS_BASE}/{nombre_tabla}"
        df = spark.read.csv(ruta, header=False, inferSchema=True).toDF(*columnas)

        total = df.count()
        filas_html = df.limit(10).toPandas().to_html(index=False, border=0)

        partes.append(f"""
        <h2>Tabla: {nombre_tabla}</h2>
        <p class="total">Total de registros: {total}</p>
        {filas_html}
        """)

    partes.append("</body></html>")

    with open(f"{OUTPUT_DIR}/index.html", "w") as f:
        f.write("\n".join(partes))

    print(f"[{time.strftime('%H:%M:%S')}] Pagina actualizada -> {OUTPUT_DIR}/index.html")

# Loop infinito: regenera el HTML cada REFRESH_SECONDS
try:
    while True:
        generar_html()
        time.sleep(REFRESH_SECONDS)
except KeyboardInterrupt:
    print("Detenido por el usuario.")
    spark.stop()
