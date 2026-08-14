#!/usr/bin/env bash
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

banner "PASO 0 - Contexto: que se pide en la Actividad 3"
echo "1) Crear un emulador de datos con OTRA estructura de retail_db"
echo "2) Crear un topico en Kafka que aloje esa nueva estructura"
echo "3) Crear un productor que envie los datos al topico"
pausa

banner "PASO 1 - Confirmar que el stack de contenedores esta arriba"
docker ps --format "table {{.Names}}\t{{.Status}}"
echo ""
echo "9 contenedores: mysql, zookeeper, kafka, namenode, datanode,"
echo "spark-master, spark-worker-1/2 y jupyter."
pausa

banner "PASO 2 - Por que NO reutilizamos orders/order_items"
docker exec -it kafka kafka-topics --list --bootstrap-server localhost:9092
echo ""
echo "Por eso disenamos un EVENTO NUEVO (denormalizado): una orden"
echo "completa en un solo JSON, con channel, payment_method, store_id."
pausa

banner "PASO 3 - Ver el emulador de datos (estructura nueva)"
cat jupyter/notebooks/emulador_retail_orders_stream.py | head -40
pausa

banner "PASO 4 - Probar el emulador de forma aislada"
docker exec -it jupyter python3 /home/jovyan/work/emulador_retail_orders_stream.py
pausa

banner "PASO 5 - Crear el topico Kafka para la nueva estructura"
docker exec -it kafka kafka-topics --create --if-not-exists \
  --topic retail_orders_stream --bootstrap-server localhost:9092 \
  --partitions 3 --replication-factor 1
docker exec -it kafka kafka-topics --describe --topic retail_orders_stream \
  --bootstrap-server localhost:9092
pausa

banner "PASO 6 - Ejecutar el productor (correr en OTRA terminal)"
echo '  docker exec -it jupyter bash -c "cd /home/jovyan/work && \'
echo '    KAFKA_BROKER=kafka:9092 KAFKA_TOPIC=retail_orders_stream \'
echo '    python3 kafka_producer_orders_stream.py --n 20 --intervalo 1"'
pausa

banner "PASO 7 - Validar con un consumidor de consola (OTRA terminal)"
echo "  docker exec -it kafka kafka-console-consumer \\"
echo "    --topic retail_orders_stream --bootstrap-server localhost:9092 \\"
echo "    --from-beginning --max-messages 20"
pausa

banner "PASO 8 - (Extra) Spark Structured Streaming"
echo "  docker exec -it jupyter bash -c \"cd /home/jovyan/work && \\"
echo "    spark-submit --master spark://spark-master:7077 \\"
echo "    --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 \\"
echo "    consumer_spark_demo.py\""
pausa

banner "CIERRE - Resumen para la exposicion"
echo "OK Emulador con estructura propia"
echo "OK Topico retail_orders_stream creado (3 particiones)"
echo "OK Productor validado con partition/offset"
echo "OK Consumo verificado"
echo "OK (Extra) Spark Structured Streaming en vivo"
