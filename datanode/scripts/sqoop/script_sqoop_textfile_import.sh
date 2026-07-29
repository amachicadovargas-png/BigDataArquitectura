#!/bin/bash
export PATH=/usr/local/sqoop/bin:$PATH

sqoop import \
  --connect "jdbc:mysql://mysql:3306/retail_db" \
  --username=root \
  --password=root \
  --table customers \
  --as-textfile \
  --target-dir=/user/datapath/datasets/customers \
  --delete-target-dir

sqoop import \
  --connect "jdbc:mysql://mysql:3306/retail_db" \
  --username=root \
  --password=root \
  --table departments \
  --as-textfile \
  --target-dir=/user/datapath/datasets/departments \
  --delete-target-dir

sqoop import \
  --connect "jdbc:mysql://mysql:3306/retail_db" \
  --username=root \
  --password=root \
  --table categories \
  --as-textfile \
  --target-dir=/user/datapath/datasets/categories \
  --delete-target-dir

sqoop import \
  --connect "jdbc:mysql://mysql:3306/retail_db" \
  --username=root \
  --password=root \
  --table orders \
  --as-textfile \
  --target-dir=/user/datapath/datasets/orders \
  --delete-target-dir

sqoop import \
  --connect "jdbc:mysql://mysql:3306/retail_db" \
  --username=root \
  --password=root \
  --table order_items \
  --as-textfile \
  --target-dir=/user/datapath/datasets/order_items \
  --delete-target-dir

sqoop import \
  --connect "jdbc:mysql://mysql:3306/retail_db" \
  --username=root \
  --password=root \
  --table products \
  --as-textfile \
  --target-dir=/user/datapath/datasets/products \
  --delete-target-dir
