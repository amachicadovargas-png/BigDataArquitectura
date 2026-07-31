CREATE DATABASE IF NOT EXISTS retail_db_cleansed;
USE retail_db_cleansed;

CREATE EXTERNAL TABLE IF NOT EXISTS top10_productos (
  product_name STRING,
  total_ventas DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION '/cleansed/top10_productos_text';

INSERT OVERWRITE TABLE top10_productos
SELECT p.product_name,
       SUM(oi.order_item_subtotal) AS total_ventas
FROM retail_db.order_items oi
JOIN retail_db.products p ON oi.order_item_product_id = p.product_id
GROUP BY p.product_name
ORDER BY total_ventas DESC
LIMIT 10;
