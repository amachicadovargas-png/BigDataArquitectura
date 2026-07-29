#!/bin/bash
# ============================================================
# PRACTICA 2 - PIPELINE DATALAKE
# Paso 7: CAPA USER - Exportar agregados de HDFS a MySQL
# Diplomante: Alfredo Manolo Machicado Vargas
# ============================================================
# Ejecutar dentro del contenedor datanode (cliente sqoop):
#   >_ sh /datanode/scripts/sqoop/script_sqoop_export_manolo.sh
#
# Requiere que las tablas destino ya existan en MySQL (ver bloque
# CREATE TABLE mas abajo, ejecutarlo antes en Adminer / mysql client)
# y que las tablas Hive de cleansed_agregaciones_manolo.hql ya
# esten materializadas.

set -e

MYSQL_HOST="mysql"
MYSQL_PORT="3306"
MYSQL_DB="bigdata_banco_union"
MYSQL_USER="root"
MYSQL_PASS="root"

# Ruta HDFS donde el warehouse de Hive dejó las tablas CLEANSED.
# Ajustar si tu hive-site.xml usa otra ruta de warehouse
# (comprobar con: hdfs dfs -ls /user/hive/warehouse/cleansed_banco_union.db)
HIVE_WAREHOUSE="/user/hive/warehouse/cleansed_banco_union.db"

echo ">> Verificando contenido de las tablas CLEANSED en HDFS ..."
/opt/hadoop-2.7.4/bin/hdfs dfs -ls ${HIVE_WAREHOUSE}/agg_transacciones_diario
/opt/hadoop-2.7.4/bin/hdfs dfs -ls ${HIVE_WAREHOUSE}/agg_metricas_diario
/opt/hadoop-2.7.4/bin/hdfs dfs -ls ${HIVE_WAREHOUSE}/agg_correlacion_riesgo_falla

# ------------------------------------------------------------
# Export: agregado de transacciones por canal/día -> MySQL
# ------------------------------------------------------------
/usr/local/sqoop/bin/sqoop export \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table agg_transacciones_diario \
  --columns fecha,canal_id,tipo_canal,ubicacion,total_transacciones,monto_total,transacciones_fallidas,tasa_fallo_pct \
  --map-column-java fecha=String \
  --export-dir ${HIVE_WAREHOUSE}/agg_transacciones_diario \
  --input-fields-terminated-by '\001' \
  --input-lines-terminated-by '\n' \
  --num-mappers 1

# ------------------------------------------------------------
# Export: agregado de métricas por servidor/día -> MySQL
# ------------------------------------------------------------
/usr/local/sqoop/bin/sqoop export \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table agg_metricas_diario \
  --columns fecha,servidor_id,nombre_servidor,ubicacion,cpu_promedio,cpu_maximo,ram_promedio,disco_promedio,lecturas_criticas,lecturas_advertencia \
  --map-column-java fecha=String \
  --export-dir ${HIVE_WAREHOUSE}/agg_metricas_diario \
  --input-fields-terminated-by '\001' \
  --input-lines-terminated-by '\n' \
  --num-mappers 1

# ------------------------------------------------------------
# Export: correlación riesgo de infraestructura vs. fallas -> MySQL
# ------------------------------------------------------------
/usr/local/sqoop/bin/sqoop export \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table agg_correlacion_riesgo_falla \
  --columns fecha,servidor_id,nombre_servidor,cpu_promedio,lecturas_criticas,total_transacciones,transacciones_fallidas,tasa_fallo_pct \
  --map-column-java fecha=String \
  --export-dir ${HIVE_WAREHOUSE}/agg_correlacion_riesgo_falla \
  --input-fields-terminated-by '\001' \
  --input-lines-terminated-by '\n' \
  --num-mappers 1

echo ">> Exportación a capa USER (MySQL) completada."

# ============================================================
# NOTA sobre el delimitador '\001':
# Las tablas Hive creadas con CTAS (CREATE TABLE ... AS SELECT)
# sin especificar ROW FORMAT usan por defecto el delimitador
# ^A (\001) entre campos, NO la coma. Por eso el export usa
# --input-fields-terminated-by '\001'.
# Si en cleansed_agregaciones_manolo.hql agregaste
# "ROW FORMAT DELIMITED FIELDS TERMINATED BY ','" a las CTAS,
# entonces cambia este flag a --input-fields-terminated-by ','
# ============================================================
