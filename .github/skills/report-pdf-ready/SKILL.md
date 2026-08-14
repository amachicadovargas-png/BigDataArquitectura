---
name: report-pdf-ready
description: "Use when preparing a PDF-ready report for academic or technical deliverables; gathering screenshots, evidence, and conclusions from logs, HDFS, database queries, and executed outputs; and formatting the final narrative for submission."
---

# PDF-Ready Report Preparation

## Purpose

Create a concise, evidence-based report suitable for PDF submission in academic or technical contexts. This skill is intended for projects involving Big Data, streaming, batch processing, and infrastructure validation.

## When to use

Use this skill when:
- an assignment requires a PDF or presentation-style report,
- screenshots and command output must be summarized for final submission,
- the user needs a structured narrative around the execution evidence,
- the project outcome must be documented with clear technical proof.

## Workflow

### 1. Gather the evidence

Collect the minimum evidence set needed to support the report:
- Docker container status
- Kafka or streaming log excerpt
- HDFS directory listing
- MySQL validation output
- final result from the batch or streaming job

Recommended commands:

```bash
docker ps --format "table {{.Names}}\t{{.Status}}"

docker exec -it jupyter grep -E "batch|materializadas en SPEED|ERROR|Exception" /tmp/streaming_v2.log | tail -20

docker exec -it namenode hdfs dfs -ls -R /lambda

docker exec -it mysql mysql -uroot -proot -e "SHOW DATABASES; USE retail_db; SHOW TABLES;"
```

### 2. Capture screenshots

Take screenshots of:
- the running container list,
- the streaming log with materialized batches,
- HDFS directory tree,
- MySQL database confirmation,
- any relevant notebook cell output.

These screenshots become the supporting evidence section in the report.

### 3. Validate the logic behind the report

Before writing the final narrative, confirm:
- the environment is up,
- the stream produced actual output,
- the batch layer wrote files to the expected location,
- the evidence lines support the final conclusion.

### 4. Draft the report structure

Use this structure:

1. Title
2. Objective
3. Architecture used
4. Execution environment
5. Evidence section
6. Results and interpretation
7. Conclusion

### 5. Write the final summary

A strong academic summary should say:
- what was implemented,
- how it was validated,
- what evidence proves it works,
- what the output demonstrates.

### 6. Prepare the final PDF narrative

Suggested wording:

> "Se implementó un flujo Lambda con capa Batch y capa Stream, utilizando Kafka, Spark, MySQL y HDFS. La validación se realizó mediante la ejecución real del pipeline y la revisión de logs, confirmando la materialización de batches en la capa SPEED y la persistencia de datos en HDFS. La evidencia obtenida demuestra que la implementación cumple con el objetivo planteado por la práctica."

## Evidence checklist

The report is complete only if it contains:
- running environment proof,
- output log proof,
- data layer proof,
- architecture explanation,
- final conclusion based on observed results.

## Quality criteria

A report is considered valid when:
- screenshots are consistent with the evidence,
- claims are supported by real command output,
- the narrative does not invent results,
- the conclusions are aligned with the observed execution.

## Example final sections

### Portada
Modelo Lambda / Big Data – Validación del entregable

### Resumen ejecutivo
Se validó la implementación del modelo Lambda con una capa Batch y una capa Stream utilizando Kafka, Spark y HDFS. La evidencia mostró la generación de batches en la capa SPEED y la persistencia de datos en HDFS, confirmando el funcionamiento del flujo.

### Evidencia
- Docker services running
- streaming log showing batch production
- HDFS listing of `/lambda`
- MySQL database available

### Conclusión
El entregable cumple con los requisitos planteados, ya que la solución fue ejecutada y validada con evidencias reales.

## Example prompts

- "Prepara el reporte PDF final con evidencia real del stream y HDFS."
- "Resume la ejecución del proyecto en formato académico para entregar como PDF."
- "Haz un reporte final con capturas, logs, y conclusión de que cumple el entregable."

## Related customizations

This skill pairs well with:
- `lambda-stack-validation`
- `report-validation-academic`
