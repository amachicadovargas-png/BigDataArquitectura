-- ============================================================
-- PRACTICA 2 - PIPELINE DATALAKE
-- Paso 7 (preparación): Tablas destino en MySQL para la CAPA USER
-- Diplomante: Alfredo Manolo Machicado Vargas
-- ============================================================
-- Ejecutar en Adminer ("Comando SQL") ANTES de correr
-- script_sqoop_export_manolo.sh. Sqoop export solo inserta filas,
-- no crea la tabla.

USE bigdata_banco_union;

DROP TABLE IF EXISTS agg_transacciones_diario;
CREATE TABLE agg_transacciones_diario (
    fecha                    DATE,
    canal_id                 INT,
    tipo_canal               VARCHAR(30),
    ubicacion                VARCHAR(50),
    total_transacciones      INT,
    monto_total               DECIMAL(14,2),
    transacciones_fallidas    INT,
    tasa_fallo_pct             DECIMAL(5,2)
);

DROP TABLE IF EXISTS agg_metricas_diario;
CREATE TABLE agg_metricas_diario (
    fecha                    DATE,
    servidor_id              INT,
    nombre_servidor          VARCHAR(50),
    ubicacion                VARCHAR(50),
    cpu_promedio             DECIMAL(5,2),
    cpu_maximo               DECIMAL(5,2),
    ram_promedio             DECIMAL(5,2),
    disco_promedio           DECIMAL(5,2),
    lecturas_criticas        INT,
    lecturas_advertencia     INT
);
