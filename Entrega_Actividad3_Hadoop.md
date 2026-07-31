# Entrega Actividad 3 - Clúster Big Data Hadoop

## 1. Objetivo
Generar un informe de la práctica que incluya:
- código de ejecución paso a paso
- configuración del clúster Hadoop con 3 DataNodes
- subida de un archivo mayor a 128 MB a HDFS
- validación de réplicas y bloques por comandos y por UI
- respaldos visuales de la práctica

---

## 2. Descripción del entorno
- Repositorio: `BigDataArquitectura`
- Archivo de configuración: `docker-compose-hadoop.yml`
- Script de ejecución corta: `scripts/activity3_short.sh`
- Cluster esperado: 1 NameNode + 3 DataNodes
- Archivo de prueba: `archivo_grande.csv` (>128 MB)
- Ruta HDFS de destino: `/temp`

---

## 3. Código de ejecución paso a paso

### 3.1 Levantar el clúster Hadoop
```bash
cd /workspaces/BigDataArquitectura
docker compose -f docker-compose-hadoop.yml up -d
```

### 3.2 Verificar los contenedores
```bash
docker ps --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
```

### 3.3 Confirmar que NameNode ve 3 DataNodes
```bash
docker exec -it namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfsadmin -report'
```

### 3.4 Generar archivo grande en el host
```bash
fallocate -l 200M archivo_grande.csv
```

Si `fallocate` no está disponible:
```bash
dd if=/dev/zero of=archivo_grande.csv bs=1M count=200 status=progress
```

### 3.5 Copiar el archivo grande al NameNode
```bash
docker cp archivo_grande.csv namenode:/home/
```

### 3.6 Subir el archivo a HDFS
```bash
docker exec -it namenode bash
export HADOOP_PREFIX=/opt/hadoop-2.7.4
export HADOOP_CONF_DIR=/etc/hadoop
export PATH="$HADOOP_PREFIX/bin:$PATH"

hdfs dfs -mkdir -p /temp
hdfs dfs -put /home/archivo_grande.csv /temp/
```

### 3.7 Verificar réplicas y bloques
```bash
hdfs getconf -confKey dfs.replication
hdfs fsck /temp/archivo_grande.csv -files -blocks
hdfs fsck /temp/archivo_grande.csv -files -blocks -locations
hdfs getconf -confKey dfs.blocksize
```

---

## 4. Script de ejecución corta

El script `scripts/activity3_short.sh` automatiza los pasos anteriores:

```bash
#!/bin/bash
set -euo pipefail

WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
COMPOSE_FILE="$WORKDIR/docker-compose-hadoop.yml"
FILE_NAME="archivo_grande.csv"
FILE_PATH="$WORKDIR/$FILE_NAME"
HDFS_PATH="/temp/$FILE_NAME"

cd "$WORKDIR"

echo "1) Levantando el clúster Hadoop..."
docker compose -f "$COMPOSE_FILE" up -d
sleep 20

echo "\n2) Verificando contenedores relevantes..."
docker ps --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

echo "\n3) Verificando que NameNode vea 3 DataNodes..."
docker exec -i namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfsadmin -report'

echo "\n4) Generando archivo de 200MB si no existe..."
if [[ ! -f "$FILE_PATH" ]]; then
  if command -v fallocate >/dev/null 2>&1; then
    fallocate -l 200M "$FILE_PATH"
  else
    dd if=/dev/zero of="$FILE_PATH" bs=1M count=200 status=progress
  fi
else
  echo "Archivo existente: $FILE_PATH"
fi

echo "\n5) Copiando archivo al contenedor NameNode..."
docker cp "$FILE_PATH" namenode:/home/

echo "\n6) Subiendo el archivo a HDFS en /temp..."
docker exec -i namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfs -mkdir -p /temp; hdfs dfs -put -f /home/$FILE_NAME /temp/'

echo "\n7) Validando configuración y bloques..."
docker exec -i namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export PATH="$HADOOP_PREFIX/bin:$PATH"; echo "--- dfs.replication ---"; hdfs getconf -confKey dfs.replication; echo "\n--- dfs.blocksize ---"; hdfs getconf -confKey dfs.blocksize; echo "\n--- hdfs fsck ---"; hdfs fsck /temp/$FILE_NAME -files -blocks -locations'

echo "\n8) Cluster listo. Revisa también la UI de NameNode en http://localhost:50080"
```

### Ejecución del script
```bash
cd /workspaces/BigDataArquitectura
scripts/activity3_short.sh
```

---

## 5. Validación en entorno gráfico

### URLs
- NameNode Web UI: `http://localhost:50080`
- YARN ResourceManager: `http://localhost:8088`

### Pasos
1. Abrir la URL de NameNode.
2. Ir a `Utilities > Browse the file system`.
3. Navegar a `/temp`.
4. Seleccionar `archivo_grande.csv`.
5. Revisar:
   - número de bloques
   - número de réplicas
   - ubicaciones de réplicas en los DataNodes

---

## 6. Evidencias y respaldos de la práctica

### Capturas sugeridas
- `pantalla_01_docker_ps.png`: salida de `docker ps` con `namenode`, `datanode`, `datanode2`, `datanode3`.
- `pantalla_02_dfsadmin_report.png`: salida de `hdfs dfsadmin -report` con `Live datanodes (3)`.
- `pantalla_03_hdfs_fsck_blocks.png`: salida de `hdfs fsck /temp/archivo_grande.csv -files -blocks`.
- `pantalla_04_hdfs_fsck_locations.png`: salida de `hdfs fsck /temp/archivo_grande.csv -files -blocks -locations`.
- `pantalla_05_namenode_ui.png`: vista de la interfaz web mostrando `/temp/archivo_grande.csv`.

### Respaldo de la práctica
Adjuntar las capturas de pantalla en el informe o en la entrega para evidenciar:
- inicio del clúster
- reconocimiento de los 3 DataNodes
- configuración de réplicas
- bloques del archivo
- ubicación de réplicas en la UI

---

## 7. Observaciones
- El clúster debe mostrar `Live datanodes (3)` en el reporte del NameNode.
- El número de bloques depende del tamaño del archivo y del valor de `dfs.blocksize`.
- Si se cambia la réplica, el archivo debe tener la nueva réplica visible en la salida de `hdfs fsck`.

---

## 8. Notas de entrega
- Incluir este documento como respaldo.
- Añadir las capturas de pantalla solicitadas.
- Asegurarse de que el archivo `archivo_grande.csv` se haya subido a `/temp` y se haya validado correctamente.
