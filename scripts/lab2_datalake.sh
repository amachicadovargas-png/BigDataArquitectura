#!/bin/bash
###############################################################################
# LABORATORIO 2 - ARQUITECTURA DATALAKE
# Script consolidado con TODOS los pasos del laboratorio guiado, en orden.
#
# IMPORTANTE:
#   - Este script asume que ya estás dentro de un GitHub Codespace con Docker
#     disponible, y que el repositorio del laboratorio ya fue clonado.
#   - Algunos pasos deben ejecutarse DENTRO de contenedores distintos
#     (datanode, hive-server, mysql). Por eso el script está dividido en
#     bloques, cada uno indicando en qué shell debe correr.
#   - No ejecutar todo el archivo de un tirón sin revisarlo: varios bloques
#     son comandos interactivos (docker exec -it, mysql, hive) que requieren
#     una sesión de terminal abierta, no un script no interactivo.
#
# Uso sugerido: copiar y pegar cada bloque en la terminal correspondiente,
# o ejecutar bloque por bloque con: bash script.sh <numero_de_paso>
###############################################################################

set -e

step_1_actualizar_repo() {
  echo ">> [Codespace host] Actualizando repositorio local"
  git fetch origin
  git reset --hard origin/master
}

step_2_levantar_contenedores() {
  echo ">> [Codespace host] Levantando contenedores de la arquitectura Hive"
  docker compose -f docker-compose.yml up -d
}

step_3_instalar_sqoop() {
  echo ">> [Dentro de datanode] Instalando/configurando Sqoop"
  echo "   Ejecutar primero: docker exec -it datanode bash"
  echo "   Luego, dentro del contenedor:"
  echo "   sh /datanode/scripts/script.sh"
}

step_4_ingesta_raw() {
  echo ">> [Dentro de datanode] Ingesta MySQL -> HDFS (capa Raw)"
  echo "   sh /datanode/scripts/sqoop/script_sqoop_textfile_import.sh"
  echo "   sh /datanode/scripts/sqoop/script_sqoop_avro.sh"
}

step_5_hive_tablas_raw() {
  echo ">> [Codespace host] Copiando scripts HQL a hive-server"
  docker cp datanode/scripts/hive/hive.hql hive-server:/opt
  docker cp datanode/scripts/hive/hive_avro.hql hive-server:/opt

  echo "   Luego entrar al contenedor: docker exec -it hive-server bash"
  echo "   Y dentro de hive-server ejecutar:"
  echo "   hive -f /opt/hive.hql"
  echo "   hive -f /opt/hive_avro.hql"
}

step_6_consulta_top10_raw() {
  cat <<'SQL'
>> [Dentro de hive-server, cliente hive] Consulta capa Raw
hive
USE retail_db_raw;

SELECT p.product_name, SUM(oi.order_item_quantity * oi.order_item_product_price) AS total_ventas
FROM retail_db_raw.order_items oi
JOIN retail_db_raw.products p ON oi.order_item_product_id = p.product_id
GROUP BY p.product_name
ORDER BY total_ventas DESC
LIMIT 10;
SQL
}

step_7_materializar_cleansed() {
  cat <<'SQL'
>> [Dentro de hive-server, cliente hive] Materializar capa Cleansed
CREATE DATABASE retail_db_cleansed;
USE DATABASE retail_db_cleansed;

CREATE EXTERNAL TABLE retail_db_cleansed.top10_productos (
  product_name STRING,
  total_ventas DOUBLE
)
STORED AS PARQUET
LOCATION '/cleansed/top10_productos_parquet';

CREATE EXTERNAL TABLE retail_db_cleansed.top10_productos (
  product_name STRING,
  total_ventas DOUBLE
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/cleansed/top10_productos_text';

INSERT OVERWRITE TABLE retail_db_cleansed.top10_productos
SELECT p.product_name, SUM(oi.order_item_subtotal) AS total_ventas
FROM retail_db_raw.order_items oi
JOIN retail_db_raw.products p ON oi.order_item_product_id = p.product_id
GROUP BY p.product_name
ORDER BY total_ventas DESC
LIMIT 10;
SQL
}

step_8_capa_usuario_mysql() {
  cat <<'SQL'
>> [Dentro del contenedor mysql, cliente mysql] Capa de usuario/presentación
mysql -u root -p

CREATE DATABASE retail_db_cleansed_rel;
USE retail_db_cleansed_rel;
CREATE TABLE top10_productos (
  product_name VARCHAR(255),
  total_ventas DOUBLE
);
SQL
}

step_9_exportar_a_mysql() {
  echo ">> [Dentro de datanode] Exportando capa Cleansed -> MySQL (capa usuario)"
  echo "   sh /datanode/scripts/sqoop/script_sqoop_textfile_export.sh"
}

usage() {
  cat <<EOF
Uso: bash script.sh <paso>
Pasos disponibles:
  1  Actualizar repositorio (git fetch/reset)
  2  Levantar contenedores (docker compose up)
  3  Instalar/configurar Sqoop en datanode
  4  Ingesta Raw: MySQL -> HDFS con Sqoop
  5  Crear tablas externas en Hive (capa Raw)
  6  Consulta top 10 productos sobre capa Raw
  7  Materializar agregación en capa Cleansed
  8  Crear base de datos y tabla en capa Usuario (MySQL)
  9  Exportar capa Cleansed -> MySQL (capa Usuario)
  all  Muestra todos los pasos en orden (informativo)
EOF
}

case "$1" in
  1) step_1_actualizar_repo ;;
  2) step_2_levantar_contenedores ;;
  3) step_3_instalar_sqoop ;;
  4) step_4_ingesta_raw ;;
  5) step_5_hive_tablas_raw ;;
  6) step_6_consulta_top10_raw ;;
  7) step_7_materializar_cleansed ;;
  8) step_8_capa_usuario_mysql ;;
  9) step_9_exportar_a_mysql ;;
  all)
    for s in step_1_actualizar_repo step_2_levantar_contenedores step_3_instalar_sqoop \
             step_4_ingesta_raw step_5_hive_tablas_raw step_6_consulta_top10_raw \
             step_7_materializar_cleansed step_8_capa_usuario_mysql step_9_exportar_a_mysql; do
      echo "############################################"
      $s
      echo
    done
    ;;
  *) usage ;;
esac
