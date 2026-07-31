# Actividad 3 - Clúster Big Data Hadoop

## Objetivo
Crear y validar un clúster Hadoop con 3 DataNodes, subir un archivo mayor a 128 MB a HDFS y verificar réplicas y bloques tanto por comandos como por la interfaz gráfica.

---

## 1) Modificar `docker-compose-hadoop.yml` para agregar un nodo DataNode y tener 3 workers

### Descripción
La práctica requiere un clúster Hadoop con:
- 1 NameNode
- 3 DataNodes
- YARN

### Contenido del archivo relevante
En `docker-compose-hadoop.yml` deben existir los servicios:
- `namenode`
- `datanode`
- `datanode2`
- `datanode3`

> En esta carpeta ya hay un compose con los 3 DataNodes definidos.

### Pasos
1. Abrir `docker-compose-hadoop.yml`.
2. Verificar que los tres servicios `datanode`, `datanode2` y `datanode3` estén definidos.
3. Confirmar que cada datanode monten volúmenes distintos y tengan una dirección IP en la red `net_pet`.

### Ejemplo del bloque `datanode3`
```yaml
  datanode3:
    build: ./datanode/
    container_name: datanode3
    volumes:
      - /tmp/hdfs/datanode3:/hadoop/dfs/data
      - ./bank:/bank
      - ./datanode:/datanode
    env_file:
      - ./hadoop-hive.env
    environment:
      SERVICE_PRECONDITION: "namenode:50070"
    depends_on:
      - namenode
    ports:
      - "50077:50075"
    networks:
      net_pet:
        ipv4_address: 172.27.1.14
```

### Explicación
- `build: ./datanode/` usa la misma imagen local de DataNode.
- `container_name` identifica el contenedor.
- El volumen `/tmp/hdfs/datanode3` guarda los datos HDFS de este worker.
- `SERVICE_PRECONDITION` espera que el NameNode esté disponible.
- La IP fija ayuda a la red interna del compose.

---

## 2) Subir un archivo mayor a 128 MB a HDFS

### Generar el archivo grande en el host
Ejecutar en el host (no dentro del contenedor):

```bash
cd /workspaces/BigDataArquitectura
fallocate -l 200M archivo_grande.csv
```

Si `fallocate` no está disponible:

```bash
dd if=/dev/zero of=archivo_grande.csv bs=1M count=200 status=progress
```

### Copiar el archivo al NameNode

```bash
docker cp archivo_grande.csv namenode:/home/
```

### Subir el archivo a HDFS desde el NameNode

```bash
docker exec -it namenode bash
export HADOOP_PREFIX=/opt/hadoop-2.7.4
export HADOOP_CONF_DIR=/etc/hadoop
export PATH="$HADOOP_PREFIX/bin:$PATH"

hdfs dfs -mkdir -p /temp
hdfs dfs -put /home/archivo_grande.csv /temp/
```

### Explicación
- `hdfs dfs -mkdir -p /temp` crea el directorio destino en HDFS.
- `hdfs dfs -put` copia el archivo local del contenedor a HDFS.

---

## 3) Validar configuraciones

### Verificar que el clúster está activo y tiene 3 DataNodes

```bash
docker compose -f docker-compose-hadoop.yml up -d

docker ps --filter "name=namenode" --filter "name=datanode" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
```

### Confirmar en NameNode

```bash
docker exec -it namenode bash -lc 'export HADOOP_PREFIX=/opt/hadoop-2.7.4; export PATH="$HADOOP_PREFIX/bin:$PATH"; hdfs dfsadmin -report'
```

Busca en la salida:
- `Live datanodes (3)`
- listado de los 3 DataNodes

### Verificar réplicas por defecto

```bash
hdfs getconf -confKey dfs.replication
```

### Verificar bloques del archivo

```bash
hdfs fsck /temp/archivo_grande.csv -files -blocks
```

### Verificar ubicación de bloques en nodos físicos

```bash
hdfs fsck /temp/archivo_grande.csv -files -blocks -locations
```

### Verificar tamaño de bloque configurado

```bash
hdfs getconf -confKey dfs.blocksize
```

### Explicación
- `dfs.replication` es el número de réplicas por bloque.
- `hdfs fsck -files -blocks` muestra cuántos bloques se generaron.
- `-locations` muestra en qué DataNodes están las réplicas.

---

## 4) Validación en la interfaz gráfica

### URLs disponibles
- NameNode Web UI: http://localhost:50080
- YARN ResourceManager: http://localhost:8088

### Qué revisar
- En NameNode Web UI, ir a "Utilities > Browse the file system"
- Navegar a `/temp`
- Seleccionar `archivo_grande.csv`
- Verificar la réplica y el número de bloques desde la vista de HDFS

---

## 5) Capturas recomendadas (pantallas de la corrida)

1. `docker compose -f docker-compose-hadoop.yml up -d` y `docker ps` mostrando `namenode`, `datanode`, `datanode2`, `datanode3`.
2. `hdfs dfsadmin -report` mostrando `Live datanodes (3)`.
3. Comando `hdfs fsck /temp/archivo_grande.csv -files -blocks` mostrando los bloques.
4. Comando `hdfs fsck /temp/archivo_grande.csv -files -blocks -locations` mostrando los nodos.
5. Pantalla de la interfaz web de NameNode con el archivo `archivo_grande.csv` y la información de réplicas.

> Inserta aquí capturas de pantalla en tu documento final si se te solicita una entrega con evidencias visuales.

---

## 6) Ejemplo de reto adicional

Cambiar la réplica del archivo a 2:

```bash
hdfs dfs -setrep -w 2 /temp/archivo_grande.csv
hdfs dfs -stat "%r" /temp/archivo_grande.csv
hdfs fsck /temp/archivo_grande.csv -files -blocks -locations
```

---

## Notas finales
- Si el clúster no arranca, revisa que no haya conflictos de puertos en `docker-compose-hadoop.yml`.
- Si necesitas borrar el clúster y los volúmenes:

```bash
docker compose -f docker-compose-hadoop.yml down -v
```

- Si prefieres usar el script ya existente, puedes ejecutar:

```bash
scripts/activity3.sh --auto
```

- Para una versión corta lista para entrega, usa:

```bash
scripts/activity3_short.sh
```


