-- ============================================================
-- PRACTICA 2 - PIPELINE DATALAKE
-- Paso 2: Crear base de datos en MySQL
-- Diplomante: Alfredo Manolo Machicado Vargas
-- ============================================================
-- Ejecutar en MySQL (por consola, Adminer -> "Comando SQL",
-- o Workbench). Esta base es el ORIGEN del pipeline; desde aqui
-- se importa a HDFS con Sqoop en el paso 4.

CREATE DATABASE IF NOT EXISTS bigdata_banco_union
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE bigdata_banco_union;

-- ------------------------------------------------------------
-- Tabla Servidores
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS servidores (
    id_servidor        INT PRIMARY KEY,
    nombre              VARCHAR(50)  NOT NULL,
    direccion_ip        VARCHAR(15),
    sistema_operativo   VARCHAR(20),
    ubicacion           VARCHAR(50),
    estado              VARCHAR(15) DEFAULT 'ACTIVO'
);

-- ------------------------------------------------------------
-- Tabla Canales
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS canales (
    id_canal        INT PRIMARY KEY,
    tipo_canal       VARCHAR(30),
    ubicacion        VARCHAR(50),
    disponibilidad   DECIMAL(5,2),
    numero_errores   INT DEFAULT 0
);

-- ------------------------------------------------------------
-- Tabla Metricas_Recursos (serie de tiempo CPU/RAM/Disco)
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS metricas_recursos (
    id_metrica    INT PRIMARY KEY,
    servidor_id   INT,
    fecha         DATETIME,
    cpu_uso       DECIMAL(5,2),
    ram_uso       DECIMAL(5,2),
    disco_uso     DECIMAL(5,2),
    FOREIGN KEY (servidor_id) REFERENCES servidores(id_servidor)
);

-- ------------------------------------------------------------
-- Tabla Logs_Sistema
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS logs_sistema (
    id_log            INT PRIMARY KEY,
    fecha             DATETIME,
    evento            VARCHAR(255),
    nivel_severidad   VARCHAR(15),
    servidor_id       INT,
    FOREIGN KEY (servidor_id) REFERENCES servidores(id_servidor)
);

-- ------------------------------------------------------------
-- Tabla Transacciones
-- ------------------------------------------------------------
CREATE TABLE IF NOT EXISTS transacciones (
    id_transaccion   INT PRIMARY KEY,
    fecha            DATETIME,
    canal_id         INT,
    monto            DECIMAL(10,2),
    estado           VARCHAR(20),
    FOREIGN KEY (canal_id) REFERENCES canales(id_canal)
);

-- ------------------------------------------------------------
-- (Alternativa a Adminer) Carga directa desde CSV vía consola MySQL.
-- Requiere que el cliente/servidor tenga local_infile habilitado:
--   SET GLOBAL local_infile = 1;
-- Ajustar la ruta a donde queden los CSV dentro del contenedor mysql.
-- ------------------------------------------------------------
-- LOAD DATA LOCAL INFILE '/csv/servidores.csv'
--   INTO TABLE servidores FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n' IGNORE 1 ROWS;
--
-- LOAD DATA LOCAL INFILE '/csv/canales.csv'
--   INTO TABLE canales FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n' IGNORE 1 ROWS;
--
-- LOAD DATA LOCAL INFILE '/csv/metricas_recursos.csv'
--   INTO TABLE metricas_recursos FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n' IGNORE 1 ROWS;
--
-- LOAD DATA LOCAL INFILE '/csv/logs_sistema.csv'
--   INTO TABLE logs_sistema FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n' IGNORE 1 ROWS;
--
-- LOAD DATA LOCAL INFILE '/csv/transacciones.csv'
--   INTO TABLE transacciones FIELDS TERMINATED BY ',' ENCLOSED BY '"'
--   LINES TERMINATED BY '\n' IGNORE 1 ROWS;
