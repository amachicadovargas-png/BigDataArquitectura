# Laboratorio 1 Hadoop

## Descripción del Repositorio
Habilita el repositorio:
- Hadoop

Todo el contenido se ejecutara en codespace de github.

## Desplegar container
1. Crea un "fork": una copia independiente de un repositorio, dentro de tu propia cuenta: 'https://github.com/vanessagironda/BigDataArquitectura'
2. Crear un codespace para el repositorio
3. Instalar la extendsion de DOCKER EXPLORER
4. Abrir terminal de codespace
5. Ejecutar el siguiente comando para desplegar los contenedores<br>
```    >docker compose -f docker-compose-hadoop.yml up     ``` <br>

#### PRACTICA 1 ## 
0. Visualizar los puertos e interfaz gráfica
```bash
   docker compose -f docker-compose-hadoop.yml ps
```
- Interfaz web del NameNode: http://localhost:50080
- Interfaz web del DataNode: http://localhost:50075 y http://localhost:50076

1. Descargar un set de datos en formato CSV
```bash
   cp python/source/student-mat.csv student-mat.csv
```
Si prefieres, también puedes usar cualquier otro archivo CSV local.

2. Copiar el archivo al contenedor del NameNode
```bash
   docker cp student-mat.csv namenode:/home/
```

3. Entrar al contenedor del NameNode
```bash
   docker exec -it namenode bash
```

4. Copiar el archivo a HDFS en la ruta /temp
```bash
   hdfs dfs -mkdir -p /temp
   hdfs dfs -put /home/student-mat.csv /temp/
```

5. Verificar la configuración de réplicas
```bash
   hdfs getconf -confKey dfs.replication
```

6. Verificar la configuración de réplicas por directorio
```bash
   hdfs fsck /temp -files -blocks
```

7. Verificar la configuración de réplicas por directorio y nodos
```bash
   hdfs fsck /temp/student-mat.csv -files -blocks -locations
```

8. Verificar el tamaño de bloque configurado
```bash
   hdfs getconf -confKey dfs.blocksize
```

## Retos

8. Cambiar la cantidad de réplicas de un archivo
```bash
   hdfs dfs -setrep -w 2 /temp/student-mat.csv
```

8. Verificar la nueva configuración
```bash
   hdfs fsck /temp/student-mat.csv -files -blocks -locations
```

8 Cambiar la cantidad de réplicas de un archivo
```    >xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx     ``` <br>
8 verificar la nueva configuracion 
```    >xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx     ``` <br>

### Error al subir cambios
```    $ git add . && git commit -m "update" && git push origin master     ``` <br>
```    error: failed to push some refs to xxxxxxxx ``` <br>
```    hint: Updates were rejected because the remote contains work that you do not``` <br>
```    hint: have locally. This is usually caused by another repository pushing to``` <br>
```    hint: the same ref. If you want to integrate the remote changes, use``` <br>
```    hint: 'git pull' before pushing again.``` <br>
```    hint: See the 'Note about fast-forwards' in 'git push --help' for details.``` <br>
```    $ git push origin master --force``` <br>
```    $ git add . && git commit -m "update" && git push origin master``` <br>
