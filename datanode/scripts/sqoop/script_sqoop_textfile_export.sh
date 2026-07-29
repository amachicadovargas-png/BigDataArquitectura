#!/bin/bash

sqoop export \
--connect "jdbc:mysql://mysql:3306/retail_db_cleansed_rel" \
--username root \
--password root \
--table top10_productos \
--export-dir /cleansed/top10_productos_text \
--input-fields-terminated-by ',' \
--num-mappers 1
