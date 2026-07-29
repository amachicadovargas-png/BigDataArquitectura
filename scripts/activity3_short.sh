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
