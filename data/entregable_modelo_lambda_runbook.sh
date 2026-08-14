#!/bin/bash
# ============================================================================
# RUNBOOK — Entregable Módulo 1: "Trabajar el modelo lambda en las capas"
# BigDataArquitectura
#
# Este script NO se corre de una sola pasada con "bash script.sh" sin mirar.
# Está dividido en FASES: correr una fase, revisar el resultado, recién
# pasar a la siguiente. Cada fase tiene su propio bloque, copialos y pegalos
# uno por uno en la terminal del Codespace.
# ============================================================================


# ============================================================================
# FASE 0 — Sincronizar con master (según el LABORATORIO 4)
# ============================================================================

git fetch origin
git reset --hard origin/master

# Verificar que quedó limpio y en el commit correcto
git status
git log -1 --oneline


# ============================================================================
# FASE 1 — (Opcional) Reset TOTAL de Docker si el ambiente viene sucio
# Solo correr esta fase si el ambiente está en mal estado (contenedores
# fantasma, volúmenes corruptos, etc.). Si docker compose ps ya muestra
# los 9 contenedores sanos, SALTEAR esta fase e ir directo a la FASE 2.
# ============================================================================

docker stop $(docker ps -q) 2>/dev/null
docker rm -f $(docker ps -aq) 2>/dev/null
docker volume rm $(docker volume ls -q) 2>/dev/null
docker network prune -f
docker system prune -a --volumes -f

# Validar que quedó todo limpio
docker ps -a
docker images
docker volume ls
docker network ls


# ============================================================================
# FASE 2 — Levantar el stack completo
# ============================================================================

docker compose -f docker-compose-actualizado.yml up -d

# Esperar ~30-40s a que todos los servicios terminen de inicializar
sleep 40

# Confirmar los 9 contenedores en estado Up
docker compose -f docker-compose-actualizado.yml ps

# Si Kafka falla con NodeExistsException (znodes efímeros huérfanos):
#   docker compose -f docker-compose-actualizado.yml restart zookeeper
#   sleep 15
#   docker compose -f docker-compose-actualizado.yml restart kafka


# ============================================================================
# FASE 3 — Validar cada pieza de la infraestructura
# ============================================================================

echo "--- Tópicos Kafka ---"
docker exec -it kafka kafka-topics --bootstrap-server kafka:9092 --list

echo "--- HDFS accesible ---"
docker exec -it namenode hdfs dfs -ls /

echo "--- Carpetas del modelo lambda en HDFS ---"
docker exec -it namenode hdfs dfs -ls /lambda 2>/dev/null || echo "Aún no existen, se crean solas al primer write"

echo "--- MySQL: conteo de orders ---"
docker exec -it mysql mysql -u root -proot -e "SELECT COUNT(*) FROM retail_db.orders;"

echo "--- IP interna de mysql (eth0), por si algún script la usa hardcodeada ---"
docker exec -it mysql sh -c "ifconfig eth0 2>/dev/null || hostname -i"


# ============================================================================
# FASE 4 — Confirmar que el emulador de datos esté insertando pedidos
# ============================================================================

echo "--- Lectura 1 ---"
docker exec -it mysql mysql -u root -proot -e "SELECT MAX(order_date) FROM retail_db.orders;"
sleep 25
echo "--- Lectura 2 (debería ser mayor que la 1) ---"
docker exec -it mysql mysql -u root -proot -e "SELECT MAX(order_date) FROM retail_db.orders;"

# Si NO avanzó, hay que arrancar el emulador manualmente. Ajustar el nombre
# real del script si es distinto (emulador_datos.py, emulador_retail_orders_stream.py, etc.)
# docker exec -d jupyter bash -c "cd /home/jovyan/work && nohup python emulador_datos.py > /tmp/emulador.log 2>&1 &"


# ============================================================================
# FASE 5 — Capa BATCH: correr "6 Batch Propuesto.ipynb" completo en background
# Usa nbconvert con timeout infinito para poder correr el while True del
# notebook sin depender de que quede un kernel de Jupyter abierto en el
# navegador (que se corta si se desconecta la sesión).
# ============================================================================

docker exec -d jupyter bash -c "cd /home/jovyan/work && nohup jupyter nbconvert --to notebook --execute --ExecutePreprocessor.timeout=-1 --output '6 Batch Propuesto - EJECUTADO.ipynb' '6 Batch Propuesto.ipynb' > /tmp/batch.log 2>&1 &"

sleep 20
echo "--- Log del batch (primeros segundos) ---"
docker exec -it jupyter cat /tmp/batch.log


# ============================================================================
# FASE 6 — Capa STREAM: correr el materializador vía spark-submit
# (probamos que "python archivo.py" solo NO anda por falta del conector de
# Kafka; el fix es usar spark-submit con --packages, como indica el propio
# docstring de materializar_stream_join_speed_v2.py)
# ============================================================================

# Primero en PRIMER PLANO para confirmar que arranca bien (Ctrl+C para cortar
# una vez que veas que no tira traceback y queda "esperando el próximo trigger"):
docker exec -it jupyter spark-submit \
  --master local[1] \
  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 \
  /home/jovyan/work/materializar_stream_join_speed_v2.py

# Una vez confirmado que anda, lanzarlo en background para que persista:
docker exec -d jupyter bash -c "cd /home/jovyan/work && nohup spark-submit --master local[1] --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 materializar_stream_join_speed_v2.py > /tmp/streaming_v2.log 2>&1 &"

sleep 45
echo "--- Log del streaming (debería mostrar micro-batches sin traceback) ---"
docker exec -it jupyter cat /tmp/streaming_v2.log

echo "--- Confirmar que el proceso spark-submit sigue vivo ---"
docker exec -it jupyter ps aux | grep -i spark-submit


# ============================================================================
# FASE 7 — Esperar a que ambas capas acumulen datos frescos
# BATCH necesita completar al menos un ciclo con JOIN (cada 20 min).
# STREAM ya debería estar escribiendo a SPEED cada 2 min.
# ============================================================================

echo "Esperando 20-25 minutos para que BATCH complete un ciclo con JOIN..."
echo "(podés cortar este sleep y seguir monitoreando manualmente con los comandos de la FASE 8)"
sleep 1500


# ============================================================================
# FASE 8 — Chequeo de frescura ANTES de correr Serving
# ============================================================================

echo "--- Último archivo en /lambda/speed ---"
docker exec -it namenode hdfs dfs -ls -R /lambda/speed | tail -5

echo "--- Último archivo en /lambda/cleansed/batch/ventas ---"
docker exec -it namenode hdfs dfs -ls -R /lambda/cleansed/batch/ventas | tail -5

echo "--- Kernels/procesos vivos ---"
docker exec -it jupyter ls /root/.local/share/jupyter/runtime/ 2>/dev/null | grep kernel
docker exec -it jupyter ps aux | grep -E "spark-submit|nbconvert"

# Repetir este bloque (FASE 8) las veces que haga falta hasta ver timestamps
# recientes (dentro de los últimos 20 min para BATCH, últimos 2 min para SPEED)
# antes de pasar a la FASE 9.


# ============================================================================
# FASE 9 — Capa SERVING: correr "7 Serving.ipynb" ajustado y capturar evidencia
# ============================================================================

docker exec -it jupyter jupyter nbconvert --to notebook --execute \
  --output "7 Serving - EJECUTADO.ipynb" \
  "/home/jovyan/work/7 Serving.ipynb"

echo "--- Convertir el notebook ejecutado a texto para leer los conteos fácil ---"
docker exec -it jupyter jupyter nbconvert --to script --stdout "/home/jovyan/work/7 Serving - EJECUTADO.ipynb" > /tmp/serving_ejecutado_local.py 2>/dev/null
docker exec -it jupyter jupyter nbconvert --to html "/home/jovyan/work/7 Serving - EJECUTADO.ipynb" --output "/tmp/serving_evidencia.html"
docker cp jupyter:/tmp/serving_evidencia.html ./serving_evidencia.html
echo "Abrí ./serving_evidencia.html en el navegador para ver la salida completa con los conteos BATCH/SPEED"


# ============================================================================
# FASE 10 — Validación final en HDFS (esto es lo que va al informe / Anexo A)
# ============================================================================

echo "=== EVIDENCIA PARA EL INFORME (copiar esta salida completa) ===" | tee /tmp/evidencia_final.txt

echo "--- /lambda/serving ---" | tee -a /tmp/evidencia_final.txt
docker exec -it namenode hdfs dfs -ls /lambda/serving | tee -a /tmp/evidencia_final.txt

echo "--- /lambda/serving/resumen_por_fuente ---" | tee -a /tmp/evidencia_final.txt
docker exec -it namenode hdfs dfs -ls /lambda/serving/resumen_por_fuente | tee -a /tmp/evidencia_final.txt

echo "--- /lambda/serving/resumen_por_status ---" | tee -a /tmp/evidencia_final.txt
docker exec -it namenode hdfs dfs -ls /lambda/serving/resumen_por_status | tee -a /tmp/evidencia_final.txt

echo "--- /lambda/serving/resumen_por_producto ---" | tee -a /tmp/evidencia_final.txt
docker exec -it namenode hdfs dfs -ls /lambda/serving/resumen_por_producto | tee -a /tmp/evidencia_final.txt

echo ""
echo "Evidencia guardada en /tmp/evidencia_final.txt"
echo "Screenshot esto + el contenido de serving_evidencia.html para pegar en el Anexo A del informe."
