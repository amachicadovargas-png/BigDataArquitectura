#!/bin/bash
set -euo pipefail

# Script para iniciar el stack de Hadoop/MySQL y validar MySQL retail_db
# Uso:
#   ./scripts/start_mysql_hadoop.sh

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"

cd "$WORKDIR"

if command -v docker >/dev/null 2>&1; then
  DOCKER_CMD="docker"
else
  echo "ERROR: docker no está instalado o no está en el PATH." >&2
  exit 1
fi

if "$DOCKER_CMD" compose version >/dev/null 2>&1; then
  COMPOSE_CMD="$DOCKER_CMD compose"
else
  if command -v docker-compose >/dev/null 2>&1; then
    COMPOSE_CMD="docker-compose"
  else
    echo "ERROR: docker compose no está disponible. Instala docker compose o docker-compose." >&2
    exit 1
  fi
fi

echo "========================================"
echo "1) Levantando contenedores con $COMPOSE_CMD"
echo "========================================"
$COMPOSE_CMD -f "$COMPOSE_FILE" up -d

sleep 10

echo "========================================"
echo "2) Estado de los contenedores relevantes"
echo "========================================"
$DOCKER_CMD ps --filter "name=mysql" --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

if ! $DOCKER_CMD ps --filter "name=mysql" --format "{{.Names}}" | grep -q mysql; then
  echo "ERROR: no se encontró el contenedor mysql. Revisa docker compose." >&2
  exit 1
fi

MYSQL_IP="$($DOCKER_CMD inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' mysql)"

cat <<EOF
========================================
3) Conexión a MySQL
========================================
Host: 127.0.0.1
Puerto: 3310
Usuario: root
Contraseña: root
Base: retail_db

Comando de prueba desde el host:
  mysql -h 127.0.0.1 -P 3310 -u root -proot -e "SHOW DATABASES; SHOW TABLES FROM retail_db;"

IP interna del contenedor mysql: $MYSQL_IP

Si deseas abrir bash dentro del contenedor:
  docker exec -it mysql bash
EOF

echo "========================================"
echo "4) Validando la base retail_db y sus tablas"
echo "========================================"
$DOCKER_CMD exec mysql mysql -uroot -proot -e "SELECT TABLE_SCHEMA, TABLE_NAME FROM information_schema.tables WHERE table_schema='retail_db' ORDER BY TABLE_NAME;"

echo ""
echo "Script completado. Si quieres ejecutar Sqoop/Hive, usa los scripts dentro de /datanode/scripts."