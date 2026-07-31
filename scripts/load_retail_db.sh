#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"
MYSQL_SERVICE="mysql"
MYSQL_DUMP="$WORKDIR/mysql/retail_db.sql"

if [[ ! -f "$MYSQL_DUMP" ]]; then
  echo "ERROR: No se encontró el dump de retail_db en $MYSQL_DUMP" >&2
  exit 1
fi

cd "$WORKDIR"

echo "=== 1) Asegurando que el servicio MySQL esté levantado ==="
docker compose -f "$COMPOSE_FILE" up -d "$MYSQL_SERVICE"

MYSQL_CONTAINER=$(docker compose -f "$COMPOSE_FILE" ps -q "$MYSQL_SERVICE")
if [[ -z "$MYSQL_CONTAINER" ]]; then
  echo "ERROR: no se pudo encontrar el contenedor MySQL después de levantarlo." >&2
  exit 1
fi

if ! docker exec -i "$MYSQL_CONTAINER" mysql -uroot -proot -e 'SELECT 1' >/dev/null 2>&1; then
  echo "ERROR: no se puede conectar a MySQL dentro del contenedor $MYSQL_CONTAINER." >&2
  exit 1
fi

EXISTING_TABLES=$(docker exec -i "$MYSQL_CONTAINER" mysql -uroot -proot -sse "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA='retail_db';")
if [[ "$EXISTING_TABLES" != "0" ]]; then
  echo "retail_db ya tiene $EXISTING_TABLES tablas. No se importará el dump."
  exit 0
fi

if docker exec -i "$MYSQL_CONTAINER" mysql -uroot -proot -e 'SHOW DATABASES LIKE "retail_db";' | grep -q retail_db; then
  echo "retail_db existe pero no contiene tablas. Procediendo a importar el dump..."
else
  echo "retail_db no existe. Procediendo a importar el dump..."
fi

TMPFILE="/tmp/retail_db.sql"
docker cp "$MYSQL_DUMP" "$MYSQL_CONTAINER":"$TMPFILE"

echo "=== 2) Importando retail_db desde el archivo dump en MySQL ==="
docker exec -i "$MYSQL_CONTAINER" bash -lc "mysql -uroot -proot < '$TMPFILE'"

echo "=== 3) Validando retail_db después de la importación ==="
docker exec -i "$MYSQL_CONTAINER" mysql -uroot -proot -e 'SELECT TABLE_SCHEMA, TABLE_NAME FROM information_schema.tables WHERE table_schema="retail_db" ORDER BY TABLE_NAME;'

echo "Importación de retail_db completada."
