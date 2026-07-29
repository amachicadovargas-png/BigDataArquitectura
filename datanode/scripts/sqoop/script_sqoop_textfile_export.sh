#!/bin/bash
set -euo pipefail

export PATH=/usr/local/sqoop/bin:$PATH

sqoop export \
  --connect "jdbc:mysql://mysql:3306/retail_db_cleansed_rel" \
  --username root \
  --password root \
  --table top10_productos \
  --export-dir /cleansed/top10_productos_text \
  --input-fields-terminated-by ',' \
  --input-null-string '\\N' \
  --input-null-non-string '\\N' \
  --num-mappers 1 \
  --batch
