#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"

cd "$WORKDIR"

echo "=== 0) Iniciando servicios necesarios ==="
docker compose -f "$COMPOSE_FILE" up -d mysql datanode hive-metastore-postgresql hive-metastore hive-server

echo "=== 1) Ingestando datos raw desde MySQL a HDFS ==="
bash "$WORKDIR/scripts/sqoop_ingest.sh"

echo "=== 2) Creando la capa user en MySQL ==="
bash "$WORKDIR/scripts/create_user_layer_mysql.sh"

echo "=== 3) Creando las tablas Hive externas de retail_db ==="
bash "$WORKDIR/scripts/create_hive_tables.sh"

echo "=== 3) Generando dataset cleansed top10_productos en HDFS ==="
docker compose -f "$COMPOSE_FILE" cp datanode/scripts/hive/hive_top10_productos.hql hive-server:/opt/hive_top10_productos.hql
docker compose -f "$COMPOSE_FILE" exec -T hive-server bash -lc '/opt/hive/bin/hive -f /opt/hive_top10_productos.hql'

echo "=== 4) Exportando cleansed top10_productos a MySQL ==="
docker compose -f "$COMPOSE_FILE" exec -T mysql bash -lc 'mysql -uroot -proot -e "USE retail_db_cleansed_rel; TRUNCATE TABLE top10_productos;"'
docker compose -f "$COMPOSE_FILE" exec -T datanode bash -lc 'bash /datanode/scripts/sqoop/script_sqoop_textfile_export.sh'

echo "=== 5) Validando exportación en MySQL ==="
docker compose -f "$COMPOSE_FILE" exec -T mysql bash -lc 'mysql -uroot -proot -B -e "USE retail_db_cleansed_rel; SELECT * FROM top10_productos LIMIT 20;"'
