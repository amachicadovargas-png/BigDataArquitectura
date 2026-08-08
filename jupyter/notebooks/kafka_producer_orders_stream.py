import argparse
import json
import os
import time

from kafka import KafkaProducer
try:
    from kafka.errors import NoBrokersAvailable
except ImportError:
    from kafka.errors import MetadataEmptyBrokerList as NoBrokersAvailable

from emulador_retail_orders_stream import generar_evento_orden


def crear_productor(broker: str) -> KafkaProducer:
    return KafkaProducer(
        bootstrap_servers=[broker],
        value_serializer=lambda v: json.dumps(v, ensure_ascii=False).encode("utf-8"),
        key_serializer=lambda k: str(k).encode("utf-8"),
        acks="all",
        retries=3,
        linger_ms=50,
    )


def main():
    parser = argparse.ArgumentParser(description="Productor Kafka de ordenes retail_db")
    parser.add_argument(
        "--broker",
        default=os.environ.get("KAFKA_BROKER", "localhost:9092"),
    )
    parser.add_argument(
        "--topic",
        default=os.environ.get("KAFKA_TOPIC", "retail_orders_stream"),
    )
    parser.add_argument("--n", type=int, default=0, help="numero de eventos a enviar (0 = infinito)")
    parser.add_argument("--intervalo", type=float, default=1.0, help="segundos entre cada envio")
    args = parser.parse_args()

    print(f"[productor] conectando a broker: {args.broker}")
    try:
        productor = crear_productor(args.broker)
    except NoBrokersAvailable:
        print(f"[productor] ERROR: no se pudo conectar a {args.broker}.")
        return

    enviados = 0
    try:
        while args.n == 0 or enviados < args.n:
            evento = generar_evento_orden()
            future = productor.send(args.topic, key=evento["order_id"], value=evento)
            metadata = future.get(timeout=10)
            enviados += 1
            print(
                f"[productor] enviado order_id={evento['order_id']} "
                f"-> topic={metadata.topic} partition={metadata.partition} "
                f"offset={metadata.offset} total=${evento['order_total']}"
            )
            time.sleep(args.intervalo)
    except KeyboardInterrupt:
        print("\n[productor] detenido manualmente")
    finally:
        productor.flush()
        productor.close()
        print(f"[productor] finalizado. eventos enviados: {enviados}")


if __name__ == "__main__":
    main()
