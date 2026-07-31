# Paso 3 — Importar la base de datos con Adminer

## 3.1 Crear la base y las tablas

1. Abrir Adminer en el navegador (normalmente `http://localhost:8080` o el
   puerto que exponga tu docker-compose del diplomado).
2. Login con las credenciales del contenedor MySQL (motor: **MySQL**,
   servidor: `mysql` o `localhost`, usuario/clave según tu `docker-compose.yml`,
   por defecto suele ser `root` / `root`).
3. En el menú superior, click en **"Comando SQL"**.
4. Pegar el contenido completo de `01_mysql_schema_origen.sql` (crea la base
   `bigdata_banco_union` y las 5 tablas) y ejecutar.

## 3.2 Cargar los datos (los 5 CSV generados)

Adminer permite importar CSV directamente a una tabla ya creada:

1. Seleccionar la base `bigdata_banco_union` en el panel izquierdo.
2. Entrar a la tabla destino (ej. `servidores`).
3. Ir a la pestaña **"Importar"**.
4. Elegir el archivo CSV correspondiente:
   - `servidores.csv` → tabla `servidores`
   - `canales.csv` → tabla `canales`
   - `metricas_recursos.csv` → tabla `metricas_recursos`
   - `logs_sistema.csv` → tabla `logs_sistema`
   - `transacciones.csv` → tabla `transacciones`
5. Formato: **CSV** (con separador `,` y primera fila como encabezado —
   Adminer detecta el encabezado automáticamente si los nombres de columna
   coinciden con los de la tabla).
6. Ejecutar la importación. Repetir para cada uno de los 5 archivos.

> **Orden recomendado de carga** (por las llaves foráneas):
> `servidores` → `canales` → `metricas_recursos` → `logs_sistema` → `transacciones`

## 3.3 Verificar

En "Comando SQL" correr:

```sql
SELECT COUNT(*) FROM servidores;          -- 6
SELECT COUNT(*) FROM canales;             -- 6
SELECT COUNT(*) FROM metricas_recursos;   -- 24192
SELECT COUNT(*) FROM logs_sistema;        -- 322
SELECT COUNT(*) FROM transacciones;       -- 33485
```

Si los conteos coinciden con los del `README.md` del dataset, la base origen
está lista para el paso 4 (ingesta a HDFS con Sqoop, capa RAW).
