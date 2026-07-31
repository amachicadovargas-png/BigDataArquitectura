#!/bin/bash
# ============================================================
# Actividad 3 - Clúster Big Data Hadoop
# Guía de ejecución paso a paso (copiar y pegar por bloques)
# ============================================================
#!/bin/bash
# ============================================================
# Actividad 3 - Clúster Big Data Hadoop
# Guía de ejecución paso a paso (copiar y pegar por bloques)
# ============================================================

set -euo pipefail

# Ajusta estas rutas si tu entorno es diferente
COMPOSE_FILE="docker-compose-hadoop.yml"
WORKDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
FILE_NAME="archivo_grande.csv"
FILE_PATH="$WORKDIR/$FILE_NAME"
HDFS_PATH="/temp/$FILE_NAME"

# ------------------------------------------------------------
# PASO 0: Ubícate en el directorio del proyecto donde está el compose
# ------------------------------------------------------------
cd "$(dirname "${BASH_SOURCE[0]}")/.."

# ------------------------------------------------------------
# PASO 1: Levantar el clúster (NameNode + 3 DataNodes)
# ------------------------------------------------------------
docker compose -f "$COMPOSE_FILE" up -d

echo "Esperar ~20-30s a que los servicios inicien..."
sleep 20

docker ps --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

# Confirmar que el NameNode reconoce 3 datanodes activos
docker exec -it namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export HADOOP_CONF_DIR=/etc/hadoop; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfsadmin -report'

# ------------------------------------------------------------
# PASO 2: Generar un archivo mayor a 128 MB (en host)
# ------------------------------------------------------------
# Ejecuta en tu host (no dentro del contenedor):
# fallocate -l 200M archivo_grande.csv
# o
# dd if=/dev/zero of=archivo_grande.csv bs=1M count=200

# ------------------------------------------------------------
# PASO 3: Copiar el archivo del host al contenedor namenode
# ------------------------------------------------------------
# docker cp archivo_grande.csv namenode:/home/

# ------------------------------------------------------------
# PASO 4: Entrar al contenedor namenode
# ------------------------------------------------------------
# docker exec -it namenode bash

# ---- Comandos que ejecutarás dentro del contenedor namenode ----
cat <<'INNER'
# PASO 5: Crear el directorio destino y subir el archivo a HDFS
hdfs dfs -mkdir -p /temp
hdfs dfs -put /home/archivo_grande.csv /temp

# PASO 6: Verificar configuración de réplicas por defecto del clúster
hdfs getconf -confKey dfs.replication

# PASO 7: Verificar bloques y réplicas del archivo subido
hdfs fsck /temp/archivo_grande.csv -files -blocks

# PASO 8: Verificar bloques, réplicas y en qué nodos físicos están
hdfs fsck /temp/archivo_grande.csv -files -blocks -locations

# PASO 9: Verificar el tamaño de bloque configurado (bytes)
hdfs getconf -confKey dfs.blocksize

# RETO 1: Cambiar la cantidad de réplicas del archivo (ej. a 2)
hdfs dfs -setrep -w 2 /temp/archivo_grande.csv

# RETO 2: Verificar la nueva configuración de réplicas
hdfs dfs -stat "%r" /temp/archivo_grande.csv
hdfs fsck /temp/archivo_grande.csv -files -blocks -locations
INNER

usage(){
	cat <<EOF
Usage: $0 [--auto] [--no-up]
	--auto    : generate 200MB file locally, copy to NameNode and upload to HDFS
	--no-up   : do not run 'docker compose up' (assume cluster already running)
EOF
	exit 1
}

AUTO=0
NO_UP=0
while [[ ${#} -gt 0 ]]; do
	case "$1" in
		--auto) AUTO=1; shift ;;
		--no-up) NO_UP=1; shift ;;
		-h|--help) usage ;;
		*) echo "Unknown arg: $1"; usage ;;
	esac
done

cd "$WORKDIR"

if [[ "$NO_UP" -eq 0 ]]; then
	echo "Levantar servicios con docker compose..."
	docker compose -f "$COMPOSE_FILE" up -d
	echo "Esperar 15s a que los servicios inicien..."
	sleep 15
fi

echo "Contenedores relevantes:"
docker ps --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"

echo "Verificando que NameNode vea los DataNodes..."
docker exec -i namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export HADOOP_CONF_DIR=/etc/hadoop; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfsadmin -report'

if [[ "$AUTO" -eq 1 ]]; then
	echo "Generando archivo de 200MB en $FILE_PATH..."
	if command -v fallocate >/dev/null 2>&1; then
		fallocate -l 200M "$FILE_PATH"
	else
		dd if=/dev/zero of="$FILE_PATH" bs=1M count=200 status=progress
	fi

	echo "Copiando $FILE_NAME al contenedor NameNode..."
	docker cp "$FILE_PATH" namenode:/home/

	echo "Subiendo a HDFS en $HDFS_PATH (se sobrescribirá si existe)..."
	docker exec -i namenode bash -lc '\
		export HADOOP_PREFIX=/opt/hadoop-2.7.4; export HADOOP_CONF_DIR=/etc/hadoop; export PATH="$HADOOP_PREFIX/bin:$PATH"; \
		hdfs dfs -rm -f "$HDFS_PATH" || true; \
		hdfs dfs -mkdir -p /temp; \
		hdfs dfs -put /home/$FILE_NAME "$HDFS_PATH"; \
		echo "--- dfs.replication ---"; hdfs getconf -confKey dfs.replication; \
		echo "--- dfs.blocksize ---"; hdfs getconf -confKey dfs.blocksize; \
		echo "--- fsck ---"; hdfs fsck "$HDFS_PATH" -files -blocks -locations'

	echo "Operación automática completada."
else
	cat <<'INNER'
Sigue estos pasos manualmente si no usas --auto:

1) Genera un archivo grande en el host:
	 fallocate -l 200M archivo_grande.csv
	 (o) dd if=/dev/zero of=archivo_grande.csv bs=1M count=200

2) Copia al NameNode y sube a HDFS:
	 docker cp archivo_grande.csv namenode:/home/
	 docker exec -it namenode bash
	 export HADOOP_PREFIX=/opt/hadoop-2.7.4
	 export HADOOP_CONF_DIR=/etc/hadoop
	 export PATH="$HADOOP_PREFIX/bin:$PATH"
	 hdfs dfs -mkdir -p /temp
	 hdfs dfs -put /home/archivo_grande.csv /temp/
	 hdfs getconf -confKey dfs.replication
	 hdfs getconf -confKey dfs.blocksize
	 hdfs fsck /temp/archivo_grande.csv -files -blocks -locations
INNER
fi

echo "Script listo: scripts/activity3.sh"
echo "Script creado: scripts/activity3.sh"
echo "Nota: crea el archivo grande en el host y realiza los pasos de copy/exec indicados."