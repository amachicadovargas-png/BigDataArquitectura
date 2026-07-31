#!/bin/bash
set -euo pipefail

# Reinicia el cluster Docker Compose y elimina los datos HDFS locales.
# Uso:
#   chmod +x scripts/reset_hadoop_cluster.sh
#   ./scripts/reset_hadoop_cluster.sh
#   ./scripts/reset_hadoop_cluster.sh --manual

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose.yml"
HDFS_DIRS=("/tmp/hdfs/namenode" "/tmp/hdfs/datanode" "/tmp/hdfs/datanode2" "/tmp/hdfs/datanode3")
MANUAL_MODE=0

while [[ $# -gt 0 ]]; do
  case "$1" in
    --manual)
      MANUAL_MODE=1
      shift
      ;;
    -h|--help)
      echo "Uso: $0 [--manual]"
      echo "  --manual  : no usa sudo y muestra los comandos exactos para ejecutar manualmente"
      exit 0
      ;;
    *)
      echo "Opción desconocida: $1"
      exit 1
      ;;
  esac
done

cd "$WORKDIR"

echo "=== Deteniendo el cluster Docker Compose ==="
docker compose -f "$COMPOSE_FILE" down

if [[ "$MANUAL_MODE" -eq 1 ]]; then
  echo "=== MODO MANUAL: no se eliminarán archivos automáticamente ==="
  echo "Ejecuta estos comandos manualmente para limpiar HDFS y reiniciar el cluster:"
  echo
  echo "  docker compose -f $COMPOSE_FILE up -d"
  echo
  echo "Si necesitas borrar los datos HDFS locales, ejecuta esto desde el host:" 
  echo
  echo "  rm -rf /tmp/hdfs/*"
  for dir in "${HDFS_DIRS[@]}"; do
    echo "  rm -rf $dir"
  done
  echo
  echo "Luego puedes levantar el cluster de nuevo con:" 
  echo "  docker compose -f $COMPOSE_FILE up -d"
  exit 0
fi

HDFS_REMOVE_CMD="rm -rf"
if command -v sudo >/dev/null 2>&1; then
  HDFS_REMOVE_CMD="sudo rm -rf"
fi

if [[ -d "/tmp/hdfs" ]]; then
  echo "=== Eliminando datos HDFS locales en /tmp/hdfs ==="
  $HDFS_REMOVE_CMD /tmp/hdfs/* || true
else
  echo "=== No existe /tmp/hdfs, no hay datos HDFS locales para borrar ==="
fi

for dir in "${HDFS_DIRS[@]}"; do
  if [[ -d "$dir" ]]; then
    echo "=== Eliminando $dir ==="
    $HDFS_REMOVE_CMD "$dir" || true
  fi
done

echo "=== Iniciando el cluster de nuevo ==="
docker compose -f "$COMPOSE_FILE" up -d

echo "=== Esperando 15 segundos para que los servicios arranquen ==="
sleep 15

echo "=== Estado actual de los contenedores relevantes ==="
docker compose -f "$COMPOSE_FILE" ps --services --filter "status=running" || true

echo "=== Si quieres ver todos los contenedores, usa: docker compose -f docker-compose.yml ps ==="
