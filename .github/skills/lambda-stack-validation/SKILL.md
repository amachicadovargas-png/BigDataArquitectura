---
name: lambda-stack-validation
description: "Use when validating a Lambda-style Big Data stack with Docker, Kafka, Spark, MySQL, and HDFS; checking whether services are up; creating Kafka topics when the CLI is unavailable; launching streaming or batch jobs; and verifying output in logs and HDFS."
---

# Lambda Stack Validation

## Purpose

Help validate and troubleshoot a Lambda architecture built with:
- Docker services for Kafka, Zookeeper, Spark, HDFS, MySQL, and Jupyter
- Batch processing from MySQL into HDFS
- Streaming processing from Kafka into the SPEED layer
- HDFS storage for RAW, CLEANSED, and SPEED outputs

This skill is designed for practical validation, not only for code review. It focuses on proving the infrastructure and pipeline are actually running and producing output.

## When to use

Use this skill when:
- a distributed data stack is not responding as expected,
- you need to confirm whether containers are running,
- Kafka topics must be created through Python because the CLI is not available,
- a Spark streaming job is suspected of not running or not producing batches,
- you are validating a Lambda batch + streaming implementation before submitting evidence.

## Workflow

### 1. Verify the infrastructure

Check whether the base services are up:

```bash
docker ps --format "table {{.Names}}\t{{.Status}}"
```

Confirm the expected containers are running:
- spark-master
- spark-worker-1
- spark-worker-2
- kafka
- zookeeper
- mysql
- namenode
- datanode
- jupyter

If some containers are missing or not healthy, start them from the project stack before continuing.

### 2. Validate critical dependencies

Check MySQL and the relevant database schema:

```bash
docker exec -it mysql mysql -uroot -proot -e "SHOW DATABASES; USE retail_db; SHOW TABLES;"
```

Check whether Kafka is reachable and whether the relevant topics exist. If the standard CLI is missing, do not assume the stack is broken; create topics via Python instead.

### 3. Handle missing Kafka CLI cleanly

If `kafka-topics.sh` is not present under the usual path, create the topics from Python using `kafka-python`:

```bash
docker exec -it jupyter python3 -m pip install kafka-python -q

docker exec -it jupyter python3 - <<'PY'
from kafka import KafkaAdminClient
from kafka.admin import NewTopic

bootstrap = "kafka:9092"
admin = KafkaAdminClient(bootstrap_servers=bootstrap, client_id="admin-topic-creator")

existing = set(admin.list_topics())
wanted = [
    NewTopic(name="orders_topic", num_partitions=1, replication_factor=1),
    NewTopic(name="order_items_topic", num_partitions=1, replication_factor=1),
]

to_create = [t for t in wanted if t.name not in existing]

if to_create:
    admin.create_topics(new_topics=to_create, validate_only=False)
    print("Topics creados:", [t.name for t in to_create])
else:
    print("Los topics ya existen:", sorted(existing))

admin.close()
PY
```

This is a valid workaround when the image layout differs from the standard Kafka distribution.

### 4. Start the data generator and streaming job

Start the internal producer or emulator for the event stream:

```bash
docker exec -d jupyter bash -lc "
cd /home/jovyan/work &&
nohup python3 emulador_datos.py > /tmp/emulador_datos.log 2>&1 &
"
```

Start the Spark streaming job:

```bash
docker exec -d jupyter bash -lc "
cd /home/jovyan/work &&
nohup spark-submit --master local[1] \
  --packages org.apache.spark:spark-sql-kafka-0-10_2.12:3.5.1 \
  --jars /opt/spark/jars/mysql-connector-j-8.4.0.jar \
  materializar_stream_join_speed_v2.py \
  > /tmp/streaming_v2.log 2>&1 &
"
```

### 5. Confirm real execution, not just startup

Check the log for batch evidence. Valid output looks like:

```bash
docker exec -it jupyter grep -E "batch|materializadas en SPEED" /tmp/streaming_v2.log | tail -20
```

Expected evidence:
- `[batch N] ... filas materializadas en SPEED.`
- batches increasing over time
- no fatal exceptions in the log

This is the critical proof that the streaming layer is working.

### 6. Validate HDFS outputs

Check the distributed filesystem structure:

```bash
docker exec -it namenode hdfs dfs -ls -R /lambda
```

Review key paths:
- `/lambda/raw/batch`
- `/lambda/cleansed/batch`
- `/lambda/speed`

If files appear in these locations, the pipeline is persisting the expected layer outputs.

### 7. Validate the batch layer

Use the batch notebook or equivalent script to execute the periodic load:

```python
import time
from pyspark.sql.functions import col, trim

RAW_PATH = "hdfs://namenode:8020/lambda/raw/batch"
CLEANSED_PATH = "hdfs://namenode:8020/lambda/cleansed/batch"

INTERVALO_RAW = 600
ciclo = 0

while True:
    ciclo += 1
    orders = spark.read.jdbc(url=mysql_url, table="orders", properties=properties)
    order_items = spark.read.jdbc(url=mysql_url, table="order_items", properties=properties)

    orders.write.mode("overwrite").parquet(f"{RAW_PATH}/orders")
    order_items.write.mode("overwrite").parquet(f"{RAW_PATH}/order_items")

    if ciclo % 2 == 0:
        orders_clean = (
            orders.filter(col("order_id").isNotNull())
            .withColumn("order_status", trim(col("order_status")))
        )
        items_clean = (
            order_items.filter(col("order_item_order_id").isNotNull())
            .filter(col("order_item_quantity") > 0)
        )

        orders_clean.write.mode("overwrite").parquet(f"{CLEANSED_PATH}/orders")
        items_clean.write.mode("overwrite").parquet(f"{CLEANSED_PATH}/order_items")

        ventas = orders_clean.join(
            items_clean,
            orders_clean.order_id == items_clean.order_item_order_id,
            "inner"
        )
        ventas.write.mode("overwrite").parquet(f"{CLEANSED_PATH}/ventas")

    time.sleep(INTERVALO_RAW)
```

### 8. Completion checks

A validation is complete when all of these are true:
- all key containers are running,
- MySQL is reachable and schema exists,
- Kafka topics exist or were created via Python,
- the Spark streaming job launches,
- log output shows actual batch materialization,
- HDFS contains the expected layer paths.

## Decision points

### If Kafka CLI is missing
Use Python with `kafka-python` to create topics and continue. This is not a failure of the exercise; it is a compatibility workaround for the current Docker image.

### If Spark starts but no batch output is produced
Check:
- topic names match the code,
- Kafka broker address is reachable,
- source data is being generated,
- the Python producer/emulator is producing events,
- logs show whether the job is stuck or waiting for data.

### If HDFS is empty
Check whether:
- the batch job ran successfully,
- paths were written with the expected mode,
- the job is still running or a previous run failed early.

## Quality expectations

A valid validation should produce:
- reproducible commands,
- direct evidence from logs or HDFS,
- no reliance on assumptions,
- clear confirmation that the stream is processing real messages rather than merely starting without output.

## Example prompts

- "Validate the Lambda stack for Kafka + Spark + MySQL + HDFS and confirm the speed layer is producing batches."
- "The Kafka CLI is missing. Create the topics from Python and verify the stream is writing output."
- "Check whether the Docker services are up and whether the batch pipeline wrote to the RAW and CLEANSED layers."
- "Diagnose why the stream job is not generating batches and prove whether the issue is infrastructure or data flow."

## Related customizations

Suggested follow-up customizations:
- a project-specific prompt for Kafka troubleshooting,
- a batch pipeline validation instruction for HDFS and MySQL checks,
- a reporting skill to turn logs and screenshots into a concise PDF-ready summary.
