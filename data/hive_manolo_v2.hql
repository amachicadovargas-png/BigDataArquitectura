-- ============================================================
-- PRACTICA 2 - PIPELINE DATALAKE
-- Paso 5: Tablas externas Hive sobre la capa RAW (HDFS)
-- Diplomante: Alfredo Manolo Machicado Vargas
-- ============================================================
-- Ejecutar dentro de hive-server:
--   >_ /opt/hive/bin/hive -f /opt/hive_manolo.hql

CREATE DATABASE IF NOT EXISTS raw_banco_union;
USE raw_banco_union;

DROP TABLE IF EXISTS servidores;
CREATE EXTERNAL TABLE servidores (
    direccion_ip        STRING,
    estado              STRING,
    id_servidor         INT,
    nombre              STRING,
    sistema_operativo   STRING,
    ubicacion           STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/servidores';

DROP TABLE IF EXISTS canales;
CREATE EXTERNAL TABLE canales (
    disponibilidad   DECIMAL(5,2),
    id_canal         INT,
    numero_errores   INT,
    tipo_canal       STRING,
    ubicacion        STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/canales';

DROP TABLE IF EXISTS metricas_recursos;
CREATE EXTERNAL TABLE metricas_recursos (
    cpu_uso       DECIMAL(5,2),
    disco_uso     DECIMAL(5,2),
    fecha         STRING,
    id_metrica    INT,
    ram_uso       DECIMAL(5,2),
    servidor_id   INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/metricas_recursos';

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

DROP TABLE IF EXISTS transacciones;
CREATE EXTERNAL TABLE transacciones (
    canal_id         INT,
    estado           STRING,
    fecha            STRING,
    id_transaccion   INT,
    monto            DECIMAL(10,2)
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/datalake/raw/banco_union/transacciones';

SELECT 'servidores' AS tabla, COUNT(*) AS filas FROM servidores
UNION ALL
SELECT 'canales', COUNT(*) FROM canales
UNION ALL
SELECT 'metricas_recursos', COUNT(*) FROM metricas_recursos
UNION ALL
SELECT 'logs_sistema', COUNT(*) FROM logs_sistema
UNION ALL
SELECT 'transacciones', C