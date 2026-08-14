---
name: report-validation-academic
description: "Use when validating academic deliverables, checking that a project meets the assignment requirements, gathering evidence from logs/HDFS/MySQL, and preparing PDF-ready screenshots and conclusions for a final report."
---

# Academic Deliverable Validation

## Purpose

This skill helps validate that a Big Data assignment is complete and defensible from an evidence perspective. It is designed for practical academic deliverables such as:
- Lambda architecture implementations
- Batch and streaming pipeline validation
- HDFS output inspection
- Kafka/Spark execution evidence
- PDF-ready report preparation

The goal is to confirm that the project is not only implemented, but also demonstrably working.

## When to use

Use this skill when:
- the user needs to verify whether the assignment is complete,
- the professor asks whether the deliverable "meets the requirements",
- screenshots are needed for a PDF report,
- evidence must be gathered from logs, filesystem, or database outputs,
- a final academic report must summarize the execution and the result clearly.

## Workflow

### 1. Check the assignment requirements

Compare the implementation against the assignment statement:
- batch layer requirements,
- stream layer requirements,
- serving layer if applicable,
- required output locations,
- required screenshots or evidence for the report.

### 2. Verify system status

Confirm the environment is active:

```bash
docker ps --format "table {{.Names}}\t{{.Status}}"
```

The key check is whether the expected services are running:
- MySQL
- Kafka/Zookeeper
- HDFS Namenode/Datanode
- Spark Master/Workers
- Jupyter

### 3. Verify data dependencies

Confirm MySQL database availability:

```bash
docker exec -it mysql mysql -uroot -proot -e "SHOW DATABASES; USE retail_db; SHOW TABLES;"
```

If Kafka topics are required, verify they exist. If the CLI is missing, use Python via `kafka-python` as the valid workaround.

### 4. Validate the pipeline execution

Check the real output using logs:

```bash
docker exec -it jupyter grep -E "batch|materializadas en SPEED|ERROR|Exception" /tmp/streaming_v2.log | tail -50
```

Evidence of success includes:
- batch counters in the log,
- rows materialized in SPEED,
- no critical exceptions,
- continuous processing behavior.

### 5. Validate HDFS storage

Check the layer paths:

```bash
docker exec -it namenode hdfs dfs -ls -R /lambda
```

Inspect expected folders:
- `/lambda/raw/batch`
- `/lambda/cleansed/batch`
- `/lambda/speed`

### 6. Validate the batch logic

Confirm the batch layer is writing outputs into HDFS and cleaning the source data:

```bash
docker exec -it namenode hdfs dfs -ls -R /lambda/raw/batch
docker exec -it namenode hdfs dfs -ls -R /lambda/cleansed/batch
```

### 7. Capture evidence for the report

Collect screenshots or command snippets for the final PDF:
- container status
- Kafka or stream log section
- HDFS directory listing
- sample batch output
- final architecture explanation

Use evidence that proves execution, not assumptions.

### 8. Draft a conclusion

A deliverable is considered valid when:
- the stack is up,
- the requested component is executing,
- outputs are produced in the expected location,
- logs show real processing evidence,
- the final report states exactly what was validated and proved.

## Decision points

### If the infrastructure is up but no pipeline output appears
Look for:
- missing Kafka topics,
- wrong topic names,
- no producer activity,
- job started but no data is available,
- missing HDFS paths or wrong schema.

### If the CLI tool is unavailable
Use Python-based validation instead of assuming it is a broken setup. For Kafka, `kafka-python` is a valid workaround when the standard CLI is not present in the image.

### If output exists but there is no report evidence
Take screenshots of:
- Docker status,
- log section with batches,
- HDFS output tree,
- any relevant SQL or file output.

## Quality criteria

The deliverable is valid if all of the following are true:
- services are running,
- required databases and topics exist,
- pipeline output is produced and saved in the expected layer,
- the evidence is captured in the final report,
- the final conclusion matches the observed results.

## Template for the academic report

### Title
Modelo Lambda / Big Data: validación de la implementación batch + streaming

### Objective
Validar la funcionalidad del flujo Lambda, confirmando que la capa Batch y la capa Stream ejecutan correctamente y materializan resultados en HDFS.

### Environment
- Docker services running
- MySQL database `retail_db`
- Kafka topics present
- Spark streaming job active
- HDFS with output paths

### Evidence
- Log with `[batch N] ... materializadas en SPEED`
- Listing of `/lambda` in HDFS
- Screenshot of running Docker containers
- Screenshot of MySQL tables or the processed output

### Conclusion
El entregable cumple con la práctica porque se verificó la ejecución real de los componentes, la persistencia en HDFS y la generación de batches en SPEED.

## Example prompts

- "Valida si el entregable cumple con la práctica y prepara evidencia para el PDF."
- "Revisa si la capa Batch y la capa Stream están funcionando y toma capturas para el reporte."
- "Confirma que el proyecto cumple con el requisito del modelo Lambda y reúne evidencia real."
- "Prepara un resumen académico con los resultados de la validación de la práctica."

## Related follow-up

A natural companion skill is one focused on PDF-ready report generation or final delivery packaging for academic assignments.
