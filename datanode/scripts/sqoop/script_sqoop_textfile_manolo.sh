#!/bin/bash
export PATH=$PATH:/opt/hadoop-2.7.4/bin:/usr/local/sqoop/bin
# ============================================================
# PRACTICA 2 - PIPELINE DATALAKE
# Paso 4: CAPA RAW - Importar la base MySQL a HDFS con Sqoop
# Diplomante: Alfredo Manolo Machicado Vargas
# ============================================================
# Ejecutar DENTRO del contenedor datanode (o el nodo con cliente sqoop):
#   >_ sh /datanode/scripts/sqoop/script_sqoop_textfile_manolo.sh
#
# Ajustar MYSQL_HOST / MYSQL_USER / MYSQL_PASS según tu docker-compose.
# ============================================================

set -e

MYSQL_HOST="mysql"
MYSQL_PORT="3306"
MYSQL_DB="bigdata_banco_union"
MYSQL_USER="root"
MYSQL_PASS="root"

HDFS_RAW_BASE="/datalake/raw/banco_union"
DIPLOMANTE="manolo"

echo ">> Creando estructura RAW en HDFS ($HDFS_RAW_BASE) ..."
hdfs dfs -mkdir -p ${HDFS_RAW_BASE}

# ------------------------------------------------------------
# Tabla: servidores
# ------------------------------------------------------------
sqoop import \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table servidores \
  --target-dir ${HDFS_RAW_BASE}/servidores \
  --delete-target-dir \
  --as-textfile \
  --fields-terminated-by ',' \
  --lines-terminated-by '\n' \
  --num-mappers 1

# ------------------------------------------------------------
# Tabla: canales
# ------------------------------------------------------------
sqoop import \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table canales \
  --target-dir ${HDFS_RAW_BASE}/canales \
  --delete-target-dir \
  --as-textfile \
  --fields-terminated-by ',' \
  --lines-terminated-by '\n' \
  --num-mappers 1

# ------------------------------------------------------------
# Tabla: metricas_recursos (usar split-by por id_metrica, más filas)
# ------------------------------------------------------------
sqoop import \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table metricas_recursos \
  --target-dir ${HDFS_RAW_BASE}/metricas_recursos \
  --delete-target-dir \
  --as-textfile \
  --fields-terminated-by ',' \
  --lines-terminated-by '\n' \
  --split-by id_metrica \
  --num-mappers 4

# ------------------------------------------------------------
# Tabla: logs_sistema
# ------------------------------------------------------------
sqoop import \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table logs_sistema \
  --target-dir ${HDFS_RAW_BASE}/logs_sistema \
  --delete-target-dir \
  --as-textfile \
  --fields-terminated-by ',' \
  --lines-terminated-by '\n' \
  --num-mappers 1

# ------------------------------------------------------------
# Tabla: transacciones (usar split-by por id_transaccion, más filas)
# ------------------------------------------------------------
sqoop import \
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \
  --username ${MYSQL_USER} \
  --password ${MYSQL_PASS} \
  --table transacciones \
  --target-dir ${HDFS_RAW_BASE}/transacciones \
  --delete-target-dir \
  --as-textfile \
  --fields-terminated-by ',' \
  --lines-terminated-by '\n' \
  --split-by id_transaccion \
  --num-mappers 4

echo ">> Verificando carga en HDFS ..."
hdfs dfs -ls -R ${HDFS_RAW_BASE}

echo ">> Listo. Capa RAW cargada por: ${DIPLOMANTE}"
