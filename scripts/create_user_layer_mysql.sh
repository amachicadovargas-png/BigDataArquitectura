#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"

echo "=== Creando base de datos retail_db_cleansed_rel y tabla top10_productos ==="
docker compose -f "$COMPOSE_FILE" exec -T mysql bash -lc 'mysql -uroot -proot -e "CREATE DATABASE IF NOT EXISTS retail_db_cleansed_rel; USE retail_db_cleansed_rel; CREATE TABLE IF NOT EXISTS top10_productos (product_name VARCHAR(255), total_ventas DOUBLE);"'

echo "=== MySQL user layer creada ==="