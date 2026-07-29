# Actividad 3 - Página de la Práctica Hadoop

## Introducción
En esta actividad se configura un clúster Hadoop con 3 DataNodes, se sube un archivo mayor a 128 MB a HDFS, y se validan réplicas y bloques tanto por línea de comandos como por la interfaz gráfica.

## 1. Preparar el clúster

### 1.1 Verificar configuración de `docker-compose-hadoop.yml`
El clúster debe tener los siguientes servicios:
- `namenode`
- `datanode`
- `datanode2`
- `datanode3`

Cada DataNode usa un volumen independiente y una IP fija en la red `net_pet`.

### 1.2 Comando para levantar el clúster
```bash
cd /workspaces/BigDataArquitectura
docker compose -f docker-compose-hadoop.yml up -d
```

### 1.3 Verificar los contenedores
```bash
docker ps --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
```

### 1.4 Confirmar que el NameNode ve 3 DataNodes
```bash
docker exec -it namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfsadmin -report'
```

> Busca en la salida `Live datanodes (3)` y la lista de los tres nodos.

## 2. Subir un archivo mayor a 128 MB a HDFS

### 2.1 Generar el archivo en el host
```bash
fallocate -l 200M archivo_grande.csv
```

Si `fallocate` no está disponible:
```bash
dd if=/dev/zero of=archivo_grande.csv bs=1M count=200 status=progress
```

### 2.2 Copiar el archivo al NameNode
```bash
docker cp archivo_grande.csv namenode:/home/
```

### 2.3 Subir el archivo a HDFS
```bash
docker exec -it namenode bash
export HADOOP_PREFIX=/opt/hadoop-2.7.4
export HADOOP_CONF_DIR=/etc/hadoop
export PATH="$HADOOP_PREFIX/bin:$PATH"

hdfs dfs -mkdir -p /temp
hdfs dfs -put /home/archivo_grande.csv /temp/
```

## 3. Validación de configuraciones

### 3.1 Validación por comandos

- Réplicas por defecto:
```bash
hdfs getconf -confKey dfs.replication
```

- Número de bloques del archivo:
```bash
hdfs fsck /temp/archivo_grande.csv -files -blocks
```

- Ubicación de bloques y réplicas físicas:
```bash
hdfs fsck /temp/archivo_grande.csv -files -blocks -locations
```

- Tamaño de bloque configurado:
```bash
hdfs getconf -confKey dfs.blocksize
```

### 3.2 Validación en interfaz gráfica

1. Abrir NameNode Web UI en `http://localhost:50080`.
2. Ir a `Utilities > Browse the file system`.
3. Navegar a `/temp`.
4. Seleccionar `archivo_grande.csv`.
5. Revisar:
   - réplica
   - número de bloques
   - ubicación de réplicas en los DataNodes

## 4. Script de ejecución corta

Este script automatiza la práctica completa:

```bash
cd /workspaces/BigDataArquitectura
scripts/activity3_short.sh
```

### Contenido del script
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

## 5. Evidencias y capturas

Para respaldar la práctica, incluir capturas de pantalla de:
- `docker ps` con `namenode`, `datanode`, `datanode2`, `datanode3`
- `hdfs dfsadmin -report` mostrando `Live datanodes (3)`
- `hdfs fsck /temp/archivo_grande.csv -files -blocks`
- `hdfs fsck /temp/archivo_grande.csv -files -blocks -locations`
- UI de NameNode con `/temp/archivo_grande.csv`

## 6. Observaciones
- El valor de `dfs.replication` indica la réplica de cada bloque.
- El total de bloques depende del tamaño del archivo y `dfs.blocksize`.
- La UI debe reflejar la misma información que los comandos.
