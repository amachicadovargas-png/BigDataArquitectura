#!/usr/bin/env bash
# ============================================================
# DEMO GUIADA - Actividad 3 Streaming Kafka Productor
# Laboratorio 4 Spark-HDFS-MySQL
#
# Uso: ejecutar paso a paso en la terminal del Codespace,
# explicando en voz alta lo que va apareciendo en pantalla.
# Cada bloque hace una pausa (Enter) para que hables antes
# de seguir al siguiente paso.
# ============================================================

pausa() {
  echo ""
  read -p ">>> Presiona ENTER para continuar con el siguiente paso... " _
  echo ""
}

banner() {
  echo ""
  echo "============================================================"
  echo "  $1"
  echo "============================================================"
}

# ------------------------------------------------------------
banner "PASO 0 - Contexto: qué se pide en la Actividad 3"
echo "1) Crear un emulador de datos con OTRA estructura de retail_db"
echo "2) Crear un tópico en Kafka que aloje esa nueva estructura"
echo "3) Crear un productor que envíe los datos al tópico"
pausa

# ------------------------------------------------------------
banner "PASO 1 - Confirmar que el stack de contenedores está arriba"
docker ps --format "table {{.Names}}\t{{.Status}}"
echo ""
echo "Explicación: 9 contenedores -> mysql, zookeeper, kafka, namenode,"
echo "datanode, spark-master, spark-worker-1/2 y jupyter."
pausa

# ------------------------------------------------------------
banner "PASO 2 - Por qué NO reutilizamos orders/order_items"
echo "El curso ya tiene un flujo que replica las tablas nativas de"
echo "retail_db 1:1 hacia orders_topic y order_items_topic:"
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092
echo ""
echo "Por eso diseñamos un EVENTO NUEVO (denormalizado): una orden"
echo "completa en un solo JSON, con campos que no existen en retail_db"
echo "(channel, payment_method, store_id)."
pausa

# ------------------------------------------------------------
banner "PASO 3 - Ver el emulador de datos (estructura nueva)"
cat jupyter/notebooks/emulador_retail_orders_stream.py | head -40
echo ""
echo "Explicación: usa Faker para generar clientes, productos y"
echo "una orden completa con canal/pago/tienda (no existen en retail_db)."
pausa

# ------------------------------------------------------------
banner "PASO 4 - Probar el emulador de forma aislada"
docker exec -it jupyter python3 /home/jovyan/work/emulador_retail_orders_stream.py
pausa

# ------------------------------------------------------------
banner "PASO 5 - Crear el tópico Kafka para la nueva estructura"
docker exec -it kafka kafka-topics --create --if-not-exists \
  --topic retail_orders_stream --bootstrap-server localhost:9092 \
  --partitions 3 --replication-factor 1
echo ""
docker exec -it kafka kafka-topics --describe --topic retail_orders_stream \
  --bootstrap-server localhost:9092
pausa

# ------------------------------------------------------------
banner "PASO 6 - Ejecutar el productor (envía datos al tópico)"
echo "Comando (correr en OTRA terminal para verlo en vivo):"
echo ""
echo '  docker exec -it jupyter bash -c "cd /home/jovyan/work && \'
echo '    KAFKA_BROKER=kafka:9092 KAFKA_TOPIC=retail_orders_stream \'
echo '    python3 kafka_producer_orders_stream.py --n 20 --intervalo 1"'
pausa

# ------------------------------------------------------------
banner "PASO 7 - Validar con un consumidor de consola"
echo "Comando (correr en paralelo al productor, en OTRA terminal):"
echo ""
echo "  docker exec -it kafka kafka-console-consumer \\"
echo "    --topic retail_orders_stream --bootstrap-server localhost:9092 \\"
echo "    --from-beginning --max-messages 20"
pausa

# ------------------------------------------------------------
banner "PASO 8 - (Extra) Consultas en vivo con Spark Structured Streaming"
echo "Comando (tarda ~1 min la primera vez, descarga el conector):"
echo ""
echo "  docker exec -it jupyter bash -c \"cd /home/jovyan/work && \\"
echo "    spark-submit --master spark://spark-master:7077 \\"
echo "    --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 \\"
echo "    consumer_spark_demo.py\""
echo ""
echo "Mientras corre, se puede abrir en el navegador:"
echo "  - puerto 8080 -> Spark Master UI (aplicación RUNNING)"
echo "  - puerto 4040 -> Structured Streaming (queries en vivo)"
pausa

# ------------------------------------------------------------
banner "CIERRE - Resumen para la exposición"
echo "✔ Emulador con estructura propia (evento denormalizado)"
echo "✔ Tópico retail_orders_stream creado (3 particiones)"
echo "✔ Productor validado: cada mensaje confirma partition/offset"
echo "✔ Consumo verificado con consumidor de consola"
echo "✔ (Extra) Consultas agregadas en tiempo real con Spark"
echo ""
echo "Fin de la demo."
