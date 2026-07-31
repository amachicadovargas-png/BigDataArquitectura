#!/bin/bash
set -e

echo "=============================================="
echo ">>> 1. Estado actual del contenedor adminer"
echo "=============================================="
docker ps -a --filter "name=adminer"

echo ""
echo "=============================================="
echo ">>> 2. Eliminando el contenedor adminer actual (sin puerto mapeado)"
echo "=============================================="
docker rm -f adminer 2>/dev/null || echo "   (no había contenedor adminer corriendo, seguimos)"

echo ""
echo "=============================================="
echo ">>> 3. Verificando que el puerto 8085 esté libre"
echo "=============================================="
CONFLICT=$(docker ps -a --filter "publish=8085" --format "{{.ID}}")
if [ -n "$CONFLICT" ]; then
    echo "   Hay un contenedor usando el puerto 8085: $CONFLICT — eliminándolo..."
    docker rm -f "$CONFLICT"
else
    echo "   OK: puerto 8085 libre."
fi

echo ""
echo "=============================================="
echo ">>> 4. Recreando adminer desde cero con --force-recreate"
echo "=============================================="
docker compose -f docker-compose.yml up -d --force-recreate adminer

echo ""
echo "=============================================="
echo ">>> 5. Verificando el mapeo de puertos"
echo "=============================================="
sleep 2
docker ps --filter "name=adminer"
echo ""
docker port adminer

echo ""
echo "=============================================="
echo ">>> 6. URL de acceso"
echo "=============================================="
if [ -n "$CODESPACE_NAME" ]; then
    echo "Adminer: https://${CODESPACE_NAME}-8085.app.github.dev/"
else
    echo "Variable CODESPACE_NAME no encontrada — abre el puerto 8085 desde la pestaña PUERTOS."
fi

echo ""
echo "=============================================="
echo ">>> LISTO. Si el paso 5 muestra '0.0.0.0:8085->80/tcp' (o similar, no vacío),"
echo ">>> el fix funcionó. Abre la URL del paso 6 en el navegador."
echo "=============================================="
