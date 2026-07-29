-- ============================================================
-- PRACTICA 2 - PIPELINE DATALAKE
-- Paso 5: Crear tablas externas en Hive sobre la capa RAW (HDFS)
-- Diplomante: Alfredo Manolo Machicado Vargas
-- ============================================================
-- Ejecutar dentro del contenedor hive-server:
--   >_ hive -f /opt/hive_manolo.hql
--
-- Los LOCATION apuntan a las carpetas que dejó Sqoop en el paso 4
-- (script_sqoop_textfile_manolo.sh -> /datalake/raw/banco_union/...)

CREATE DATABASE IF NOT EXISTS raw_banco_union;
USE raw_banco_union;

-- ------------------------------------------------------------
-- Externa: servidores
-- ------------------------------------------------------------
DROP TABLE IF EXISTS servidores;
CREATE EXTERNAL TABLE servidores (
    id_servidor        INT,
    nombre              STRING,
    direccion_ip        STRING,
    sistema_operativo   STRING,
    ubicacion           STRING,
    estado               STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/servidores';

-- ------------------------------------------------------------
-- Externa: canales
-- ------------------------------------------------------------
DROP TABLE IF EXISTS canales;
CREATE EXTERNAL TABLE canales (
    id_canal        INT,
    tipo_canal       STRING,
    ubicacion        STRING,
    disponibilidad   DECIMAL(5,2),
    numero_errores   INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/canales';

-- ------------------------------------------------------------
-- Externa: metricas_recursos
-- ------------------------------------------------------------
DROP TABLE IF EXISTS metricas_recursos;
CREATE EXTERNAL TABLE metricas_recursos (
    id_metrica    INT,
    servidor_id   INT,
    fecha         STRING,   -- se castea a timestamp en la capa CLEANSED
    cpu_uso       DECIMAL(5,2),
    ram_uso       DECIMAL(5,2),
    disco_uso     DECIMAL(5,2)
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/metricas_recursos';

-- ------------------------------------------------------------
-- Externa: logs_sistema
-- ------------------------------------------------------------
DROP TABLE IF EXISTS logs_sistema;
CREATE EXTERNAL TABLE logs_sistema (
    id_log            INT,
    fecha             STRING,
    evento            STRING,
    nivel_severidad   STRING,
    servidor_id       INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/logs_sistema';

-- ------------------------------------------------------------
-- Externa: transacciones
-- ------------------------------------------------------------
DROP TABLE IF EXISTS transacciones;
CREATE EXTERNAL TABLE transacciones (
    id_transaccion   INT,
    fecha            STRING,
    canal_id         INT,
    monto            DECIMAL(10,2),
    estado           STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/transacciones';

-- ------------------------------------------------------------
-- Verificacion rápida
-- ------------------------------------------------------------
SELECT 'servidores' AS tabla, COUNT(*) AS filas FROM servidores
UNION ALL
SELECT 'canales', COUNT(*) FROM canales
UNION ALL
SELECT 'metricas_recursos', COUNT(*) FROM metricas_recursos
UNION ALL
SELECT 'logs_sistema', COUNT(*) FROM logs_sistema
UNION ALL
SELECT 'transacciones', COUNT(*) FROM transacciones;
