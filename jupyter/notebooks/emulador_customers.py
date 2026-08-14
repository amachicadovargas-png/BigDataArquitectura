import mysql.connector
import time
from faker import Faker

fake = Faker()

conn = mysql.connector.connect(
    host="mysql",
    port=3306,
    user="root",
    password="root",
    database="retail_db"
)
cursor = conn.cursor()

print("Iniciando emulador de customers...")

while True:
    customer = (
        fake.first_name(),
        fake.last_name(),
        fake.unique.email(),
        fake.password(length=10),
        fake.street_address(),
        fake.city(),
        fake.state_abbr(),
        fake.zipcode()
    )
    sql = """
        INSERT INTO customers
        (customer_fname, customer_lname, customer_email, customer_password,
         customer_street, customer_city, customer_state, customer_zipcode)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s)
    """
    cursor.execute(sql, customer)
    conn.commit()
    print("Insertado:", customer)
    time.sleep(2)


