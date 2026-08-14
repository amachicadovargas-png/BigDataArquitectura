#!/bin/bash
# ============================================================
# Laboratorio 4 - Spark / HDFS / MySQL
# Script de gestión del entorno Docker en Codespaces
# Repositorio: BigDataArquitectura
# ============================================================

set -e

COMPOSE_FILE="docker-compose-actualizado.yml"

banner() {
  echo ""
  echo "============================================================"
  echo " $1"
  echo "============================================================"
}

pausa() {
  read -p "Presiona ENTER para continuar..." _
}

# ------------------------------------------------------------
# 1. Sincronizar repositorio
# ------------------------------------------------------------
sync_repo() {
  banner "PASO 1 - Sincronizar repositorio"
  git fetch origin
  git reset --hard origin/master
  echo "Repositorio sincronizado con origin/master."
}

# ------------------------------------------------------------
# 2. Levantar contenedores
# ------------------------------------------------------------
levantar_contenedores() {
  banner "PASO 2 - Levantar contenedores"
  docker compose -f "$COMPOSE_FILE" up -d
  echo ""
  echo "Contenedores solicitados. Validando estado..."
  sleep 3
  docker ps -a
}

# ------------------------------------------------------------
# 3. Validar entorno
# ------------------------------------------------------------
validar_entorno() {
  banner "PASO 3 - Validación del entorno"

  echo "--- Contenedores ---"
  docker ps -a
  echo ""

  echo "--- Puerto MySQL (esperado 3310) ---"
  docker port mysql || echo "mysql no está corriendo"
  echo ""

  echo "--- Puerto Jupyter (esperado 8200 -> 8888, 4040-4050) ---"
  docker port jupyter || echo "jupyter no está corriendo"
  echo ""

  echo "--- Puerto Spark Master (esperado 8080, 7077) ---"
  docker port spark-master || echo "spark-master no está corriendo"
  echo ""

  echo "--- Notebooks disponibles en jupyter ---"
  docker exec jupyter ls /home/jovyan/work || echo "No se pudo listar /home/jovyan/work"
  echo ""

  echo "--- Tablas en MySQL (retail_db) ---"
  docker exec mysql mysql -u root -proot -e "USE retail_db; SHOW TABLES;" 2>/dev/null || echo "No se pudo conectar a MySQL todavía (puede tardar unos segundos en iniciar)"
  echo ""

  echo "--- Tópicos en Kafka ---"
  docker exec kafka kafka-topics --list --bootstrap-server localhost:9092 2>/dev/null || echo "No se pudo listar tópicos de Kafka"
  echo ""

  echo "--- HDFS: nodos vivos ---"
  docker exec namenode hdfs dfsadmin -report 2>/dev/null | grep -A 2 "Live datanodes" || echo "No se pudo consultar HDFS"
}

# ------------------------------------------------------------
# 4. Obtener IP de un contenedor (eth0)
# ------------------------------------------------------------
obtener_ip() {
  banner "Obtener IP de un contenedor (eth0)"
  read -p "Nombre del contenedor (ej. mysql, jupyter, spark-master): " CONT
  docker exec -it "$CONT" bash -c "ifconfig eth0 2>/dev/null || ip addr show eth0"
}

# ------------------------------------------------------------
# 5. Reconstruir solo jupyter (liviano)
# ------------------------------------------------------------
rebuild_jupyter_liviano() {
  banner "Reconstruir jupyter (liviano, con caché)"
  docker compose -f "$COMPOSE_FILE" up -d --build jupyter
}

# ------------------------------------------------------------
# 6. Reconstruir solo jupyter (forzado, sin caché)
# ------------------------------------------------------------
rebuild_jupyter_forzado() {
  banner "Reconstruir jupyter (forzado, sin caché)"
  docker compose -f "$COMPOSE_FILE" build --no-cache jupyter
  docker compose -f "$COMPOSE_FILE" up -d jupyter
}

# ------------------------------------------------------------
# 7. Limpieza completa de Docker
# ------------------------------------------------------------
limpiar_docker() {
  banner "Limpieza completa de Docker"
  echo "Esto detendrá y eliminará TODOS los contenedores, imágenes, volúmenes y redes personalizadas."
  read -p "¿Continuar? (s/n): " CONFIRM
  if [ "$CONFIRM" != "s" ]; then
    echo "Cancelado."
    return
  fi

  echo "1) Deteniendo contenedores..."
  docker stop $(docker ps -q) 2>/dev/null || echo "  (no hay contenedores corriendo)"

  echo "2) Eliminando contenedores..."
  docker rm -f $(docker ps -aq) 2>/dev/null || echo "  (no hay contenedores para eliminar)"

  echo "3) Eliminando imágenes..."
  docker rmi -f $(docker images -q) 2>/dev/null || echo "  (no hay imágenes para eliminar)"

  echo "4) Eliminando volúmenes..."
  docker volume rm $(docker volume ls -q) 2>/dev/null || echo "  (no hay volúmenes para eliminar)"

  echo "5) Eliminando redes no predeterminadas..."
  docker network prune -f

  echo "6) Limpieza final del sistema..."
  docker system prune -a --volumes -f

  echo "Limpieza completa. Corre la opción 'Levantar contenedores' para reconstruir todo."
}

# ------------------------------------------------------------
# 8. Abrir notebook (instrucciones + token)
# ------------------------------------------------------------
abrir_notebook() {
  banner "Abrir el notebook de la práctica"
  echo "1. En la pestaña PUERTOS de VS Code, abre el puerto 8200 (Jupyter Lab)."
  echo "2. Si pide token, aquí está el log de jupyter con la URL/token:"
  echo ""
  docker logs jupyter 2>&1 | grep -i "token\|127.0.0.1" | tail -5
  echo ""
  echo "3. Dentro de Jupyter Lab, abre:  3. Practica Mysql Hdfs Spark.ipynb"
}

# ------------------------------------------------------------
# 9. Ejecutar script de la Actividad 3 (demo streaming)
# ------------------------------------------------------------
ejecutar_actividad3() {
  banner "Ejecutar demo_actividad3.sh (Kafka + Spark Structured Streaming)"
  if [ -f "data/demo_actividad3.sh" ]; then
    bash data/demo_actividad3.sh
  else
    echo "No se encontró data/demo_actividad3.sh. Ajusta la ruta manualmente:"
    echo "  bash <ruta>/demo_actividad3.sh"
  fi
}

# ------------------------------------------------------------
# Menú principal
# ------------------------------------------------------------
menu() {
  while true; do
    banner "LABORATORIO 4 - SPARK / HDFS / MYSQL - MENÚ"
    echo "1) Sincronizar repositorio (git fetch + reset --hard)"
    echo "2) Levantar contenedores (docker compose up -d)"
    echo "3) Validar entorno (contenedores, puertos, MySQL, Kafka, HDFS)"
    echo "4) Obtener IP de un contenedor (eth0)"
    echo "5) Reconstruir jupyter (liviano, con caché)"
    echo "6) Reconstruir jupyter (forzado, sin caché)"
    echo "7) Limpieza completa de Docker"
    echo "8) Abrir notebook (instrucciones + token)"
    echo "9) Ejecutar demo Actividad 3 (Kafka + Spark Streaming)"
    echo "0) Salir"
    echo ""
    read -p "Elige una opción: " OPCION

    case $OPCION in
      1) sync_repo ;;
      2) levantar_contenedores ;;
      3) validar_entorno ;;
      4) obtener_ip ;;
      5) rebuild_jupyter_liviano ;;
      6) rebuild_jupyter_forzado ;;
      7) limpiar_docker ;;
      8) abrir_notebook ;;
      9) ejecutar_actividad3 ;;
      0) echo "Saliendo..."; exit 0 ;;
      *) echo "Opción inválida." ;;
    esac
    pausa
  done
}

menu
