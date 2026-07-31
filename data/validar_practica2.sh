#!/bin/bash
# ============================================================
# PRACTICA 2 - PIPELINE DATALAKE
# Script de validación de los 7 pasos requeridos
# ============================================================
# Ejecutar desde la raíz del repo: bash validar_practica2.sh

RESULTS=()
check() {
    local step="$1"
    local desc="$2"
    local cmd="$3"
    if eval "$cmd" > /tmp/validar_out.txt 2>&1; then
        RESULTS+=("✅ Paso $step: $desc")
        return 0
    else
        RESULTS+=("❌ Paso $step: $desc")
        echo "   --- detalle del fallo ---"
        tail -5 /tmp/validar_out.txt | sed 's/^/   /'
        return 1
    fi
}

echo "=============================================="
echo "VALIDANDO PRACTICA 2 - PIPELINE DATALAKE"
echo "=============================================="
echo ""

# ------------------------------------------------------------
# Paso 1: Dataset CSV
# ------------------------------------------------------------
echo ">>> Paso 1: Verificando datasets CSV..."
CSV_COUNT=$(find data -maxdepth 1 -name "*.csv" 2>/dev/null | wc -l)
if [ "$CSV_COUNT" -gt 0 ]; then
    RESULTS+=("✅ Paso 1: $CSV_COUNT archivo(s) CSV encontrado(s) en data/")
else
    RESULTS+=("❌ Paso 1: no se encontraron archivos CSV en data/")
fi

# ------------------------------------------------------------
# Paso 2: Base de datos MySQL creada (schema origen)
# ------------------------------------------------------------
echo ">>> Paso 2: Verificando schema MySQL de origen..."
check "2" "schema SQL de origen existe" \
    "test -f data/01_mysql_schema_origen.sql"

echo ">>> Paso 2: Verificando que la BD exista en MySQL..."
check "2" "base de datos existe en MySQL" \
    "docker compose -f docker-compose.yml exec -T mysql bash -lc \"mysql -uroot -proot -e 'SHOW DATABASES;'\" | grep -qi banco_union"

# ------------------------------------------------------------
# Paso 3: Import con Adminer (verificamos que las tablas de origen tengan datos)
# ------------------------------------------------------------
echo ">>> Paso 3: Verificando tablas de origen con datos (import vía Adminer)..."
check "3" "tablas de origen en MySQL tienen filas" \
    "docker compose -f docker-compose.yml exec -T mysql bash -lc \"mysql -uroot -proot -D bigdata_banco_union -e 'SELECT COUNT(*) FROM transacciones;'\" | grep -qv '^0$'"

# ------------------------------------------------------------
# Paso 4: CAPA RAW - Sqoop import a HDFS
# ------------------------------------------------------------
echo ">>> Paso 4: Verificando script de Sqoop import..."
check "4" "script de sqoop import existe" \
    "test -f data/script_sqoop_textfile_manolo.sh"

echo ">>> Paso 4: Verificando datos en HDFS (capa raw)..."
check "4" "datos presentes en HDFS raw" \
    "docker compose -f docker-compose.yml exec -T datanode bash -lc \"/opt/hadoop-2.7.4/bin/hdfs dfs -ls /datalake/raw/banco_union/transacciones\" | grep -q part"

# ------------------------------------------------------------
# Paso 5: Tabla externa en Hive
# ------------------------------------------------------------
echo ">>> Paso 5: Verificando script HQL de tablas externas..."
check "5" "script hql de tablas externas existe" \
    "test -f data/hive_manolo_v2.hql"

echo ">>> Paso 5: Verificando que las tablas externas tengan datos legibles..."
check "5" "tabla externa transacciones legible sin NULL en canal_id" \
    "docker compose -f docker-compose.yml exec -T hive-server bash -lc \"/opt/hive/bin/hive -e 'USE raw_banco_union; SELECT canal_id FROM transacciones LIMIT 1;'\" | tail -1 | grep -qv -E '^(NULL)?$'"

# ------------------------------------------------------------
# Paso 6: CAPA CLEANSED - agregaciones
# ------------------------------------------------------------
echo ">>> Paso 6: Verificando script de agregaciones..."
check "6" "script de agregaciones cleansed existe" \
    "test -f data/cleansed_agregaciones_manolo.hql"

echo ">>> Paso 6: Verificando que las 3 tablas agregadas tengan datos..."
CLEANSED_OK=true
for tbl in agg_transacciones_diario agg_metricas_diario agg_correlacion_riesgo_falla; do
    COUNT=$(docker compose -f docker-compose.yml exec -T hive-server bash -lc \
        "/opt/hive/bin/hive -e 'USE cleansed_banco_union; SELECT COUNT(*) FROM $tbl;'" 2>/dev/null | tail -1 | tr -d '\r')
    if [ "$COUNT" -gt 0 ] 2>/dev/null; then
        RESULTS+=("✅ Paso 6: $tbl tiene $COUNT filas")
    else
        RESULTS+=("❌ Paso 6: $tbl vacía o no existe")
        CLEANSED_OK=false
    fi
done

# ------------------------------------------------------------
# Paso 7: CAPA USER - export a MySQL
# ------------------------------------------------------------
echo ">>> Paso 7: Verificando script de sqoop export..."
check "7" "script de sqoop export existe" \
    "test -f data/script_sqoop_export_manolo.sh"

echo ">>> Paso 7: Verificando que las tablas destino en MySQL tengan datos..."
USER_OK=true
for tbl in agg_transacciones_diario agg_metricas_diario agg_correlacion_riesgo_falla; do
    COUNT=$(docker compose -f docker-compose.yml exec -T mysql bash -lc \
        "mysql -uroot -proot -D bigdata_banco_union -e 'SELECT COUNT(*) FROM $tbl;' -s -N" 2>/dev/null | tr -d '\r')
    if [ "$COUNT" -gt 0 ] 2>/dev/null; then
        RESULTS+=("✅ Paso 7: $tbl (MySQL) tiene $COUNT filas")
    else
        RESULTS+=("❌ Paso 7: $tbl (MySQL) vacía o no existe")
        USER_OK=false
    fi
done

# ------------------------------------------------------------
# Resumen final
# ------------------------------------------------------------
echo ""
echo "=============================================="
echo "RESUMEN DE VALIDACIÓN"
echo "=============================================="
FAIL_COUNT=0
for r in "${RESULTS[@]}"; do
    echo "$r"
    if [[ "$r" == ❌* ]]; then
        FAIL_COUNT=$((FAIL_COUNT+1))
    fi
done

echo ""
if [ "$FAIL_COUNT" -eq 0 ]; then
    echo "🎉 TODOS LOS PASOS CUMPLEN. Pipeline completo y validado."
else
    echo "⚠️  $FAIL_COUNT verificación(es) fallaron. Revisa el detalle arriba."
fi
echo "=============================================="
