#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"
MYSQL_SERVICE="mysql"
MYSQL_DUMP="$WORKDIR/mysql/vanessa_math_student.sql"

if [[ ! -f "$MYSQL_DUMP" ]]; then
  echo "ERROR: No se encontró el dump de bd_vanessa en $MYSQL_DUMP" >&2
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

EXISTING_TABLES=$(docker exec -i "$MYSQL_CONTAINER" mysql -uroot -proot -sse "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA='bd_vanessa';")
if [[ "$EXISTING_TABLES" != "0" ]]; then
  echo "bd_vanessa ya tiene $EXISTING_TABLES tablas. No se importará el dump."
  exit 0
fi

echo "=== 2) Importando bd_vanessa desde el archivo dump en MySQL ==="
TMPFILE="/tmp/vanessa_math_student.sql"
docker cp "$MYSQL_DUMP" "$MYSQL_CONTAINER":"$TMPFILE"
docker exec -i "$MYSQL_CONTAINER" bash -lc "mysql -uroot -proot < '$TMPFILE'"

echo "=== 3) Validando bd_vanessa después de la importación ==="
docker exec -i "$MYSQL_CONTAINER" mysql -uroot -proot -e 'SELECT TABLE_SCHEMA, TABLE_NAME FROM information_schema.tables WHERE table_schema="bd_vanessa" ORDER BY TABLE_NAME;'

echo "Importación de bd_vanessa completada."
