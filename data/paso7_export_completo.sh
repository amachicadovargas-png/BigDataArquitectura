#!/bin/bash
set -e

SQL_FILE="data/03_mysql_tablas_capa_user.sql"
SQOOP_FILE="data/script_sqoop_export_manolo.sh"

echo "=============================================="
echo ">>> 1. Agregando tabla agg_correlacion_riesgo_falla al .sql"
echo "=============================================="
python3 - "$SQL_FILE" << 'PYEOF'
import sys

path = sys.argv[1]
with open(path, "r", encoding="utf-8") as f:
    content = f.read()

marker = "CREATE TABLE agg_metricas_diario ("
if marker not in content:
    print("ERROR: no se encontró el bloque de agg_metricas_diario en el .sql.")
    sys.exit(1)

# Si ya se agregó antes, no duplicar
if "agg_correlacion_riesgo_falla" in content:
    print("OK: la tabla agg_correlacion_riesgo_falla ya existe en el archivo, no se duplica.")
else:
    extra_table = """

DROP TABLE IF EXISTS agg_correlacion_riesgo_falla;
CREATE TABLE agg_correlacion_riesgo_falla (
    fecha                    DATE,
    servidor_id              INT,
    nombre_servidor          VARCHAR(50),
    cpu_promedio             DECIMAL(5,2),
    lecturas_criticas        INT,
    total_transacciones      INT,
    transacciones_fallidas   INT,
    tasa_fallo_pct           DECIMAL(5,2)
);
"""
    content = content.rstrip() + "\n" + extra_table
    with open(path, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: tabla agg_correlacion_riesgo_falla agregada al .sql")
PYEOF

echo ""
echo "=============================================="
echo ">>> 2. Agregando bloque de export al script sqoop"
echo "=============================================="
python3 - "$SQOOP_FILE" << 'PYEOF'
import sys

path = sys.argv[1]
with open(path, "r", encoding="utf-8") as f:
    content = f.read()

if "--table agg_correlacion_riesgo_falla" in content:
    print("OK: el bloque de export para agg_correlacion_riesgo_falla ya existe, no se duplica.")
else:
    # 1. Agregar la verificación de HDFS junto a las otras dos
    old_check = 'hdfs dfs -ls ${HIVE_WAREHOUSE}/agg_metricas_diario'
    new_check = old_check + '\nhdfs dfs -ls ${HIVE_WAREHOUSE}/agg_correlacion_riesgo_falla'
    if old_check not in content:
        print("ERROR: no se encontró la línea de verificación de agg_metricas_diario.")
        sys.exit(1)
    content = content.replace(old_check, new_check)

    # 2. Agregar el bloque de export antes del echo final
    marker = 'echo ">> Exportación a capa USER (MySQL) completada."'
    if marker not in content:
        print("ERROR: no se encontró el marcador final del script.")
        sys.exit(1)

    export_block = """# ------------------------------------------------------------
# Export: correlación riesgo de infraestructura vs. fallas -> MySQL
# ------------------------------------------------------------
sqoop export \\
  --connect "jdbc:mysql://${MYSQL_HOST}:${MYSQL_PORT}/${MYSQL_DB}" \\
  --username ${MYSQL_USER} \\
  --password ${MYSQL_PASS} \\
  --table agg_correlacion_riesgo_falla \\
  --export-dir ${HIVE_WAREHOUSE}/agg_correlacion_riesgo_falla \\
  --input-fields-terminated-by '\\001' \\
  --input-lines-terminated-by '\\n' \\
  --num-mappers 1

"""
    content = content.replace(marker, export_block + marker)

    with open(path, "w", encoding="utf-8") as f:
        f.write(content)
    print("OK: bloque de export agregado al script sqoop.")
PYEOF

echo ""
echo "=============================================="
echo ">>> 3. Verificando ambos archivos actualizados"
echo "=============================================="
echo "--- $SQL_FILE ---"
grep -n -A 10 "agg_correlacion_riesgo_falla" "$SQL_FILE" | head -15
echo ""
echo "--- $SQOOP_FILE ---"
grep -n -A 10 "agg_correlacion_riesgo_falla" "$SQOOP_FILE" | head -15

echo ""
echo "=============================================="
echo ">>> 4. Creando las tablas destino en MySQL"
echo "=============================================="
docker compose -f docker-compose.yml exec -T mysql bash -lc "mysql -uroot -proot bigdata_banco_union" < "$SQL_FILE"
echo "OK: tablas creadas/recreadas en MySQL."

echo ""
echo "=============================================="
echo ">>> 5. Copiando el script sqoop actualizado al datanode"
echo "=============================================="
docker compose -f docker-compose.yml cp "$SQOOP_FILE" datanode:/datanode/scripts/sqoop/script_sqoop_export_manolo.sh
echo "OK: script copiado."

echo ""
echo "=============================================="
echo ">>> 6. Ejecutando el export completo (3 tablas)"
echo "=============================================="
docker compose -f docker-compose.yml exec -T datanode bash -lc "sh /datanode/scripts/sqoop/script_sqoop_export_manolo.sh"

echo ""
echo "=============================================="
echo ">>> 7. Verificando filas cargadas en MySQL"
echo "=============================================="
docker compose -f docker-compose.yml exec -T mysql bash -lc "mysql -uroot -proot bigdata_banco_union -e \"SELECT 'agg_transacciones_diario' AS tabla, COUNT(*) AS filas FROM agg_transacciones_diario UNION ALL SELECT 'agg_metricas_diario', COUNT(*) FROM agg_metricas_diario UNION ALL SELECT 'agg_correlacion_riesgo_falla', COUNT(*) FROM agg_correlacion_riesgo_falla;\""

echo ""
echo "=============================================="
echo ">>> LISTO. Si las 3 tablas muestran filas (84, 84, 24), el Paso 7 y el pipeline completo quedaron cerrados."
echo "=============================================="
