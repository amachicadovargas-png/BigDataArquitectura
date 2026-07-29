#!/bin/bash
# =====================================================================
# check_stack.sh
# Verifica el estado del stack levantado con:
#   docker-compose -f docker-compose-spark.yml up
#
# Uso:
#   chmod +x check_stack.sh
#   ./check_stack.sh
# =====================================================================

COMPOSE_FILE="docker-compose-spark.yml"

# Colores para salida legible
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo "======================================================"
echo " 1) Estado de los contenedores (docker-compose ps)"
echo "======================================================"
docker-compose -f "$COMPOSE_FILE" ps
echo ""

echo "======================================================"
echo " 2) Verificando que cada contenedor esté 'Up'"
echo "======================================================"
CONTAINERS=$(docker-compose -f "$COMPOSE_FILE" ps -q)
FAILED=0

for c in $CONTAINERS; do
    NAME=$(docker inspect --format='{{.Name}}' "$c" | sed 's/^\///')
    STATUS=$(docker inspect --format='{{.State.Status}}' "$c")
    RESTARTS=$(docker inspect --format='{{.RestartCount}}' "$c")

    if [ "$STATUS" == "running" ]; then
        echo -e "${GREEN}[OK]${NC} $NAME -> running (restarts: $RESTARTS)"
    else
        echo -e "${RED}[FALLO]${NC} $NAME -> $STATUS (restarts: $RESTARTS)"
        FAILED=1
    fi
done
echo ""

echo "======================================================"
echo " 3) Últimas líneas de log de cada contenedor"
echo "======================================================"
for c in $CONTAINERS; do
    NAME=$(docker inspect --format='{{.Name}}' "$c" | sed 's/^\///')
    echo -e "${YELLOW}--- Logs de $NAME (últimas 10 líneas) ---${NC}"
    docker logs --tail 10 "$c" 2>&1
    echo ""
done

echo "======================================================"
echo " 4) Verificando interfaces web (HTTP)"
echo "======================================================"

check_url () {
    local NAME=$1
    local URL=$2
    CODE=$(curl -s -o /dev/null -w "%{http_code}" --max-time 5 "$URL")
    if [[ "$CODE" =~ ^2|3 ]]; then
        echo -e "${GREEN}[OK]${NC} $NAME ($URL) -> HTTP $CODE"
    else
        echo -e "${RED}[FALLO]${NC} $NAME ($URL) -> HTTP $CODE (o no responde)"
        FAILED=1
    fi
}

check_url "Namenode HDFS UI"   "http://localhost:50080"
check_url "Datanode UI"        "http://localhost:50075"
check_url "Spark Master UI"    "http://localhost:8080"
check_url "Spark Worker 1 UI"  "http://localhost:8081"
check_url "Spark Worker 2 UI"  "http://localhost:8082"
check_url "Jupyter Lab"        "http://localhost:8200/lab"
echo ""

echo "======================================================"
echo " 5) Workers registrados en Spark Master"
echo "======================================================"
WORKERS=$(curl -s --max-time 5 "http://localhost:8080" | grep -o "Alive Workers.*" | head -1)
if [ -n "$WORKERS" ]; then
    echo -e "${GREEN}[OK]${NC} Detectado: $WORKERS"
else
    echo -e "${YELLOW}[AVISO]${NC} No se pudo confirmar workers desde el HTML (revisa manualmente en el navegador)."
fi
echo ""

echo "======================================================"
echo " 6) Estado del cluster HDFS (dfsadmin -report)"
echo "======================================================"
NAMENODE_CONTAINER=$(docker ps --filter "name=namenode" --format "{{.Names}}" | head -1)
if [ -n "$NAMENODE_CONTAINER" ]; then
    docker exec "$NAMENODE_CONTAINER" hdfs dfsadmin -report 2>&1 | head -20
else
    echo -e "${RED}[FALLO]${NC} No se encontró contenedor namenode."
fi
echo ""

echo "======================================================"
if [ $FAILED -eq 0 ]; then
    echo -e "${GREEN} RESULTADO: Todos los servicios y endpoints responden correctamente.${NC}"
else
    echo -e "${RED} RESULTADO: Se detectaron fallos. Revisa los logs marcados arriba.${NC}"
fi
echo "======================================================"
