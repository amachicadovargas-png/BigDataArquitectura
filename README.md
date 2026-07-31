# Práctica 2 — Pipeline Datalake (Banco Unión S.A.)

Diplomante: **Alfredo Manolo Machicado Vargas**
Dataset base: el generado en el Proyecto Final (`servidores`, `canales`,
`metricas_recursos`, `logs_sistema`, `transacciones`).

## Flujo del pipeline

```
CSV (1)
  └─► MySQL "bigdata_banco_union"  (2)
        └─► Adminer import         (3)
              └─► Sqoop import ────────► HDFS /datalake/raw/...   CAPA RAW (4)
                    └─► Hive EXTERNAL TABLE sobre HDFS             (5)
                          └─► Hive GROUP BY -> tablas materializadas
                              (agg_transacciones_diario, agg_metricas_diario)
                                                                    CAPA CLEANSED (6)
                                └─► Sqoop export ─────► MySQL       CAPA USER (7)
```

## Orden de ejecución

| # | Archivo | Dónde se ejecuta | Qué hace |
|---|---------|-------------------|----------|
| 1 | `servidores.csv`, `canales.csv`, `metricas_recursos.csv`, `logs_sistema.csv`, `transacciones.csv` | — | Dataset fuente (del Proyecto Final) |
| 2 | `01_mysql_schema_origen.sql` | Adminer / MySQL | Crea la BD `bigdata_banco_union` y las 5 tablas origen |
| 3 | `02_importar_con_adminer.md` | Adminer (GUI) | Guía para cargar los 5 CSV a las tablas |
| 4 | `script_sqoop_textfile_manolo.sh` | contenedor `datanode` | Importa las 5 tablas MySQL → HDFS (`/datalake/raw/banco_union/...`) |
| 5 | `hive_manolo.hql` | contenedor `hive-server` (`hive -f`) | Crea 5 tablas EXTERNAS en Hive sobre la capa RAW |
| 6 | `cleansed_agregaciones_manolo.hql` | contenedor `hive-server` (`hive` interactivo o `hive -f`) | Agregaciones GROUP BY → 3 tablas materializadas (capa CLEANSED) |
| 7a | `03_mysql_tablas_capa_user.sql` | Adminer / MySQL | Crea las tablas destino en MySQL antes del export |
| 7b | `script_sqoop_export_manolo.sh` | contenedor `datanode` | Exporta las tablas CLEANSED de HDFS a MySQL (capa USER) |

## Qué agregaciones se construyeron y por qué

- **`agg_transacciones_diario`**: total y monto de transacciones, fallidas y
  tasa de fallo, por canal y por día.
- **`agg_metricas_diario`**: CPU/RAM/disco promedio y máximo, y conteo de
  lecturas en nivel crítico/advertencia, por servidor y por día.
- **`agg_correlacion_riesgo_falla`**: cruce de las dos anteriores en los días
  con lecturas críticas de CPU — es la evidencia tabular de la hipótesis
  central del Proyecto Final (CPU alta + volumen de transacciones →
  transacciones fallidas), la misma que se ilustra en la sección 10 del
  documento ("Predicción de Falla ATM").

## Notas prácticas

- Ajustar `MYSQL_HOST`, `MYSQL_USER`, `MYSQL_PASS` en ambos scripts `.sh`
  según las credenciales reales de tu `docker-compose.yml` del laboratorio.
- Verificar la ruta del warehouse de Hive
  (`hdfs dfs -ls /user/hive/warehouse/cleansed_banco_union.db`) antes de
  correr el export — puede variar según la configuración del clúster.
- Las tablas CTAS de Hive sin `ROW FORMAT` explícito usan `\001` como
  delimitador de campo; por eso el export usa
  `--input-fields-terminated-by '\001'` (ver nota al final de
  `script_sqoop_export_manolo.sh`).
