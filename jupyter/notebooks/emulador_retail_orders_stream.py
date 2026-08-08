import random
import uuid
from datetime import datetime, timezone

from faker import Faker

fake = Faker("es_ES")

CATEGORIAS = [
    (1, "Electronica"),
    (2, "Ropa"),
    (3, "Calzado"),
    (4, "Hogar"),
    (5, "Deportes"),
    (6, "Juguetes"),
]

PRODUCTOS = [
    (101, "Smartphone Galaxy", 1, 1899.0),
    (102, "Audifonos Bluetooth", 1, 249.0),
    (103, "Laptop 14 pulgadas", 1, 4599.0),
    (201, "Polera Basica", 2, 79.0),
    (202, "Pantalon Jean", 2, 189.0),
    (301, "Zapatillas Running", 3, 459.0),
    (302, "Botines Cuero", 3, 399.0),
    (401, "Set de Sartenes", 4, 329.0),
    (402, "Lampara LED", 4, 99.0),
    (501, "Balon de Futbol", 5, 129.0),
    (601, "Muneca Interactiva", 6, 219.0),
]

CANALES = ["WEB", "MOBILE_APP", "TIENDA_FISICA"]
METODOS_PAGO = ["TARJETA_CREDITO", "TARJETA_DEBITO", "QR", "EFECTIVO"]
ESTADOS_ORDEN = ["PENDIENTE", "PROCESANDO", "COMPLETO", "CANCELADO"]
TIENDAS = list(range(1, 6))

_order_id_counter = 100000
_customer_id_counter = 5000


def _next_order_id() -> int:
    global _order_id_counter
    _order_id_counter += 1
    return _order_id_counter


def generar_evento_orden() -> dict:
    global _customer_id_counter
    _customer_id_counter += 1

    n_items = random.randint(1, 4)
    productos_elegidos = random.sample(PRODUCTOS, k=n_items)

    items = []
    total = 0.0
    for pid, pname, cat_id, precio in productos_elegidos:
        cantidad = random.randint(1, 3)
        subtotal = round(precio * cantidad, 2)
        total += subtotal
        items.append(
            {
                "product_id": pid,
                "product_name": pname,
                "category_id": cat_id,
                "quantity": cantidad,
                "unit_price": precio,
                "subtotal": subtotal,
            }
        )

    evento = {
        "event_id": str(uuid.uuid4()),
        "event_timestamp": datetime.now(timezone.utc).isoformat(),
        "order_id": _next_order_id(),
        "customer_id": _customer_id_counter,
        "customer_fname": fake.first_name(),
        "customer_lname": fake.last_name(),
        "customer_city": fake.city(),
        "customer_state": fake.state(),
        "order_status": random.choice(ESTADOS_ORDEN),
        "channel": random.choice(CANALES),
        "payment_method": random.choice(METODOS_PAGO),
        "store_id": random.choice(TIENDAS),
        "items": items,
        "order_total": round(total, 2),
    }
    return evento


if __name__ == "__main__":
    import json

    for _ in range(3):
        print(json.dumps(generar_evento_orden(), indent=2, ensure_ascii=False))
