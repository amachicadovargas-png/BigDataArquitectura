#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"
HIVE_SERVICES=(hive-metastore-postgresql hive-metastore hive-server)

cd "$WORKDIR"

echo "=== 0) Levantando servicios Hive ==="
docker compose -f "$COMPOSE_FILE" up -d "${HIVE_SERVICES[@]}"

echo "=== 1) Esperando a que Hive Metastore esté disponible ==="
# Esperar a que hive-server pueda comunicarse con el metastore.
for i in $(seq 1 60); do
  if docker compose -f "$COMPOSE_FILE" exec -T hive-server bash -lc '/opt/hive/bin/hive -S -e "SHOW DATABASES;" >/dev/null 2>&1'; then
    echo "Hive Metastore listo"
    break
  fi
  echo "  esperando Hive Metastore... ($i/60)"
  sleep 2
  if [[ $i -eq 60 ]]; then
    echo "ERROR: Hive Metastore no respondió en 120 segundos" >&2
    exit 1
  fi
 done

echo "=== 2) Copiando scripts Hive al contenedor hive-server ==="
docker compose -f "$COMPOSE_FILE" cp datanode/scripts/hive/hive.hql hive-server:/opt/hive.hql
docker compose -f "$COMPOSE_FILE" cp datanode/scripts/hive/hive_avro.hql hive-server:/opt/hive_avro.hql

echo "=== 3) Copiando esquemas Avro a HDFS ==="
docker compose -f "$COMPOSE_FILE" exec -T datanode bash -lc '/opt/hadoop-2.7.4/bin/hadoop fs -mkdir -p /user/datapath/datasets/avro && /opt/hadoop-2.7.4/bin/hadoop fs -put -f /datanode/categories.avsc /user/datapath/datasets/avro/ && /opt/hadoop-2.7.4/bin/hadoop fs -put -f /datanode/customers.avsc /user/datapath/datasets/avro/ && /opt/hadoop-2.7.4/bin/hadoop fs -put -f /datanode/departments.avsc /user/datapath/datasets/avro/ && /opt/hadoop-2.7.4/bin/hadoop fs -put -f /datanode/order_items.avsc /user/datapath/datasets/avro/ && /opt/hadoop-2.7.4/bin/hadoop fs -put -f /datanode/orders.avsc /user/datapath/datasets/avro/ && /opt/hadoop-2.7.4/bin/hadoop fs -put -f /datanode/products.avsc /user/datapath/datasets/avro/'

echo "=== 4) Ejecutando scripts Hive ==="
docker compose -f "$COMPOSE_FILE" exec -T hive-server bash -lc '/opt/hive/bin/hive -f /opt/hive.hql'

docker compose -f "$COMPOSE_FILE" exec -T hive-server bash -lc '/opt/hive/bin/hive -f /opt/hive_avro.hql'

cat <<EOF
=== Hive tables created ===
Revisa las tablas con:
  docker compose -f "$COMPOSE_FILE" exec -T hive-server bash -lc 'hive -e "USE retail_db; SHOW TABLES;"'

Si quieres verificar los directorios en HDFS:
  docker compose -f "$COMPOSE_FILE" exec -T datanode bash -lc '/opt/hadoop-2.7.4/bin/hadoop fs -ls /user/datapath/datasets'
  docker compose -f "$COMPOSE_FILE" exec -T datanode bash -lc '/opt/hadoop-2.7.4/bin/hadoop fs -ls /user/datapath/datasets/avro'
EOF
