-- ============================================================
-- PRACTICA 2 - PIPELINE DATALAKE
-- Paso 6: CAPA CLEANSED - Agregaciones sobre las tablas externas
-- Diplomante: Alfredo Manolo Machicado Vargas
-- ============================================================
-- Ejecutar interactivamente en hive-server:
--   >_ hive
--   hive> source /opt/cleansed_agregaciones_manolo.hql;
-- (o bien: hive -f /opt/cleansed_agregaciones_manolo.hql)

CREATE DATABASE IF NOT EXISTS cleansed_banco_union;
USE cleansed_banco_union;

-- ------------------------------------------------------------
-- Agregado 1: Transacciones por canal y día
-- (total transacciones, monto total, fallidas, tasa de fallo)
-- Esta tabla queda MATERIALIZADA en HDFS (gestionada por Hive)
-- lista para exportarse a MySQL en el paso 7.
-- ------------------------------------------------------------
DROP TABLE IF EXISTS agg_transacciones_diario;
CREATE TABLE agg_transacciones_diario
STORED AS TEXTFILE
AS
SELECT
    TO_DATE(t.fecha)                                   AS fecha,
    t.canal_id                                          AS canal_id,
    c.tipo_canal                                        AS tipo_canal,
    c.ubicacion                                          AS ubicacion,
    COUNT(*)                                            AS total_transacciones,
    ROUND(SUM(t.monto), 2)                              AS monto_total,
    SUM(CASE WHEN t.estado = 'FALLIDO' THEN 1 ELSE 0 END) AS transacciones_fallidas,
    ROUND(
        SUM(CASE WHEN t.estado = 'FALLIDO' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2
    )                                                    AS tasa_fallo_pct
FROM raw_banco_union.transacciones t
JOIN raw_banco_union.canales c ON t.canal_id = c.id_canal
GROUP BY TO_DATE(t.fecha), t.canal_id, c.tipo_canal, c.ubicacion;

-- ------------------------------------------------------------
-- Agregado 2: Métricas de recursos por servidor y día
-- (promedios, máximos, y horas en nivel de riesgo)
-- ------------------------------------------------------------
DROP TABLE IF EXISTS agg_metricas_diario;
CREATE TABLE agg_metricas_diario
STORED AS TEXTFILE
AS
SELECT
    TO_DATE(m.fecha)                                    AS fecha,
    m.servidor_id                                        AS servidor_id,
    s.nombre                                              AS nombre_servidor,
    s.ubicacion                                           AS ubicacion,
    ROUND(AVG(m.cpu_uso), 2)                             AS cpu_promedio,
    ROUND(MAX(m.cpu_uso), 2)                             AS cpu_maximo,
    ROUND(AVG(m.ram_uso), 2)                             AS ram_promedio,
    ROUND(AVG(m.disco_uso), 2)                           AS disco_promedio,
    SUM(CASE WHEN m.cpu_uso > 90 THEN 1 ELSE 0 END)      AS lecturas_criticas,
    SUM(CASE WHEN m.cpu_uso > 75 AND m.cpu_uso <= 90 THEN 1 ELSE 0 END) AS lecturas_advertencia
FROM raw_banco_union.metricas_recursos m
JOIN raw_banco_union.servidores s ON m.servidor_id = s.id_servidor
GROUP BY TO_DATE(m.fecha), m.servidor_id, s.nombre, s.ubicacion;

-- ------------------------------------------------------------
-- Agregado 3: Correlación riesgo de infraestructura vs. fallas
-- (evidencia cuantitativa de la hipótesis del proyecto final:
--  CPU alta + volumen de transacciones -> más transacciones fallidas)
-- ------------------------------------------------------------
DROP TABLE IF EXISTS agg_correlacion_riesgo_falla;
CREATE TABLE agg_correlacion_riesgo_falla
STORED AS TEXTFILE
AS
SELECT
    a.fecha,
    a.servidor_id,
    a.nombre_servidor,
    a.cpu_promedio,
    a.lecturas_criticas,
    t.total_transacciones,
    t.transacciones_fallidas,
    t.tasa_fallo_pct
FROM agg_metricas_diario a
JOIN agg_transacciones_diario t
  ON a.fecha = t.fecha
WHERE a.lecturas_criticas > 0
ORDER BY a.fecha, a.cpu_promedio DESC;

-- ------------------------------------------------------------
-- Verificacion
-- ------------------------------------------------------------
SELECT * FROM agg_transacciones_diario ORDER BY fecha, canal_id LIMIT 20;
SELECT * FROM agg_metricas_diario ORDER BY fecha, servidor_id LIMIT 20;
SELECT * FROM agg_correlacion_riesgo_falla LIMIT 20;
