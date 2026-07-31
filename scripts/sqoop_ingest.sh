#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"
DATANODE_SERVICE="datanode"
MYSQL_SERVICE="mysql"
LOAD_RETAIL_SCRIPT="$WORKDIR/scripts/load_retail_db.sh"

cd "$WORKDIR"

echo "=== 0) Asegurando que los servicios estén levantados ==="
docker compose -f "$COMPOSE_FILE" up -d "$MYSQL_SERVICE" "$DATANODE_SERVICE"

echo "=== 1) Asegurando que retail_db esté poblada en MySQL ==="
if [[ ! -x "$LOAD_RETAIL_SCRIPT" ]]; then
  chmod +x "$LOAD_RETAIL_SCRIPT" || true
fi
bash "$LOAD_RETAIL_SCRIPT"

echo "=== 1.1) Asegurando que bd_vanessa esté poblada en MySQL ==="
if [[ ! -x "$WORKDIR/scripts/load_bd_vanessa.sh" ]]; then
  chmod +x "$WORKDIR/scripts/load_bd_vanessa.sh" || true
fi
bash "$WORKDIR/scripts/load_bd_vanessa.sh"

echo "=== 2) Preparar Sqoop en el contenedor datanode ==="
docker compose -f "$COMPOSE_FILE" exec -T "$DATANODE_SERVICE" bash -lc 'sh /datanode/scripts/script.sh'

echo "=== 3) Importar tablas MySQL a HDFS como texto ==="
docker compose -f "$COMPOSE_FILE" exec -T "$DATANODE_SERVICE" bash -lc 'sh /datanode/scripts/sqoop/script_sqoop_textfile.sh'

echo "=== 4) Importar tablas MySQL a HDFS como Avro ==="
docker compose -f "$COMPOSE_FILE" exec -T "$DATANODE_SERVICE" bash -lc 'sh /datanode/scripts/sqoop/script_sqoop_avro.sh'

cat <<EOF
=== Sqoop ingestion finished ===
Revisa logs dentro del contenedor $DATANODE_SERVICE:
  docker compose -f "$COMPOSE_FILE" exec -T $DATANODE_SERVICE bash
  ls -l /tmp/log_*.log

Para comprobar los datos en HDFS:
  docker compose -f "$COMPOSE_FILE" exec -T $DATANODE_SERVICE bash -lc '/opt/hadoop-2.7.4/bin/hadoop fs -ls /user/datapath/datasets'
  docker compose -f "$COMPOSE_FILE" exec -T $DATANODE_SERVICE bash -lc '/opt/hadoop-2.7.4/bin/hadoop fs -ls /user/datapath/datasets/avro'

Para crear automáticamente las tablas Hive externas, ejecuta:
  ./scripts/create_hive_tables.sh
EOF
