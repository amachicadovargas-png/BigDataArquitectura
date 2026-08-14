#!/bin/bash
# ============================================================
# Actividad 3 - Captura de evidencia para entrega/exposición
# Guarda en /evidencia_actividad3/ los logs de cada paso,
# con fecha y hora, listos para adjuntar o mostrar.
# ============================================================

set -e

TOPIC="retail_orders_stream"
BROKER="kafka:9092"
CARPETA="evidencia_actividad3"
TS=$(date +%Y%m%d_%H%M%S)clear

mkdir -p "$CARPETA"

banner() {
  echo ""
  echo "============================================================"
  echo " $1"
  echo "============================================================"
}

# ------------------------------------------------------------
# 1. Emulador (prueba aislada)
# ------------------------------------------------------------
capturar_emulador() {
  banner "Capturando salida del emulador"
  ARCHIVO="$CARPETA/01_emulador_${TS}.log"
  docker exec jupyter python3 /home/jovyan/work/emulador_retail_orders_stream.py > "$ARCHIVO" 2>&1
  echo "Guardado en: $ARCHIVO"
}

# ------------------------------------------------------------
# 2. Tópico Kafka (estado)
# ------------------------------------------------------------
capturar_topico() {
  banner "Capturando estado del tópico Kafka"
  ARCHIVO="$CARPETA/02_topico_${TS}.log"
  {
    echo "--- Lista de tópicos ---"
    docker exec kafka kafka-topics --list --bootstrap-server localhost:9092
    echo ""
    echo "--- Detalle de $TOPIC ---"
    docker exec kafka kafka-topics --describe --topic "$TOPIC" --bootstrap-server localhost:9092
  } > "$ARCHIVO" 2>&1
  echo "Guardado en: $ARCHIVO"
}

# ------------------------------------------------------------
# 3. Productor (real, N mensajes)
# ------------------------------------------------------------
capturar_productor() {
  banner "Ejecutando y capturando el productor"
  read -p "Cantidad de mensajes a enviar [20]: " N
  N=${N:-20}
  ARCHIVO="$CARPETA/03_productor_${TS}.log"
  docker exec -it jupyter bash -c "cd /home/jovyan/work && KAFKA_BROKER=$BROKER KAFKA_TOPIC=$TOPIC python3 kafka_producer_orders_stream.py --n $N --intervalo 1" | tee "$ARCHIVO"
  echo "Guardado en: $ARCHIVO"
}

# ------------------------------------------------------------
# 4. Consumidor de consola
# ------------------------------------------------------------
capturar_consumidor() {
  banner "Ejecutando y capturando el consumidor de consola"
  read -p "Cantidad de mensajes a leer [20]: " N
  N=${N:-20}
  ARCHIVO="$CARPETA/04_consumidor_${TS}.log"
  docker exec -it kafka kafka-console-consumer --topic "$TOPIC" --bootstrap-server localhost:9092 --from-beginning --max-messages "$N" | tee "$ARCHIVO"
  echo "Guardado en: $ARCHIVO"
}

# ------------------------------------------------------------
# 5. Spark Structured Streaming (job con límite de tiempo)
# ------------------------------------------------------------
capturar_spark() {
  banner "Ejecutando Spark Structured Streaming (con límite de tiempo)"
  read -p "Segundos que debe correr antes de detenerse [60]: " SEG
  SEG=${SEG:-60}
  ARCHIVO="$CARPETA/05_spark_streaming_${TS}.log"

  echo "El job correrá $SEG segundos y luego se detendrá automáticamente."
  echo "Mientras corre, puedes abrir en el navegador:"
  echo "  - puerto 8080 -> Spark Master UI"
  echo "  - puerto 4040 -> Structured Streaming UI"
  echo ""

  docker exec jupyter bash -c "cd /home/jovyan/work && timeout ${SEG}s spark-submit --master spark://spark-master:7077 --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 consumer_spark_demo.py" \
    > "$ARCHIVO" 2>&1 || true

  echo "Guardado en: $ARCHIVO"
  echo ""
  echo "--- Resumen de batches capturados ---"
  grep -A 6 "Batch:" "$ARCHIVO" || echo "(no se alcanzó a procesar ningún batch en el tiempo dado, prueba con más segundos)"
}

# ------------------------------------------------------------
# 6. Ejecutar todo en secuencia
# ------------------------------------------------------------
capturar_todo() {
  capturar_emulador
  capturar_topico
  capturar_productor
  sleep 2
  capturar_consumidor
  capturar_spark
  banner "Evidencia completa generada en ./$CARPETA/"
  ls -la "$CARPETA"
}

# ------------------------------------------------------------
# Menú
# ------------------------------------------------------------
menu() {
  while true; do
    banner "CAPTURA DE EVIDENCIA - ACTIVIDAD 3"
    echo "1) Capturar emulador"
    echo "2) Capturar estado del tópico Kafka"
    echo "3) Capturar productor (real)"
    echo "4) Capturar consumidor de consola"
    echo "5) Capturar Spark Structured Streaming (con límite de tiempo)"
    echo "6) Capturar TODO en secuencia"
    echo "0) Salir"
    echo ""
    read -p "Elige una opción: " OPCION

    case $OPCION in
      1) capturar_emulador ;;
      2) capturar_topico ;;
      3) capturar_productor ;;
      4) capturar_consumidor ;;
      5) capturar_spark ;;
      6) capturar_todo ;;
      0) echo "Saliendo..."; exit 0 ;;
      *) echo "Opción inválida." ;;
    esac
    read -p "Presiona ENTER para continuar..." _
  done
}

menu
