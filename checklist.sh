#!/bin/bash
# ============================================================
# check_stack.sh - Checklist de estado del stack Big Data
# Banco Unión S.A. - Diplomado Big Data
# ============================================================

echo "=============================================="
echo "   CHECKLIST DE CONTENEDORES - BIG DATA STACK"
echo "=============================================="
echo ""

# Lista de servicios esperados (ajusta nombres si difieren)
SERVICES=("namenode" "datanode" "resourcemanager" "nodemanager" "historyserver" "hive-server" "hive-metastore" "spark-master" "spark-worker" "jupyter" "mysql")

for svc in "${SERVICES[@]}"; do
    # Busca contenedores cuyo nombre contenga el string del servicio
    match=$(docker ps --format "{{.Names}}|{{.Status}}" | grep -i "$svc")

    if [ -z "$match" ]; then
        # No encontrado corriendo -> revisa si existe pero está caído
        exists=$(docker ps -a --format "{{.Names}}|{{.Status}}" | grep -i "$svc")
        if [ -z "$exists" ]; then
            printf "  [ NO EXISTE ]  %-18s -> contenedor no encontrado\n" "$svc"
        else
            printf "  [ CAIDO     ]  %-18s -> %s\n" "$svc" "$(echo "$exists" | cut -d'|' -f2)"
        fi
    else
        name=$(echo "$match" | head -1 | cut -d'|' -f1)
        status=$(echo "$match" | head -1 | cut -d'|' -f2)
        printf "  [ ARRIBA    ]  %-18s -> %s\n" "$name" "$status"
    fi
done

echo ""
echo "=============================================="
echo "   RESUMEN GENERAL (docker ps)"
echo "=============================================="
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

echo ""
echo "=============================================="
echo "   CONTENEDORES CAIDOS / CON ERROR"
echo "=============================================="
down=$(docker ps -a --filter "status=exited" --format "table {{.Names}}\t{{.Status}}")
if [ -z "$down" ] || [ "$(echo "$down" | wc -l)" -eq 1 ]; then
    echo "  (ninguno - todo lo que existe está arriba)"
else
    echo "$down"
fi

echo ""
echo "=============================================="
echo "   FIN DEL CHECKLIST"
echo "=============================================="
