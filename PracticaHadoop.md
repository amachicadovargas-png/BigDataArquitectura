# Laboratorio 1 Hadoop

## Descripción del Repositorio
Habilita el repositorio:

- Hadoop
- Hive

Todo el contenido se ejecutara en codespace de github.

## Desplegar container

1. Crear un codespace para el repositorio e ingresar al mismo
2. Abrir terminal de codespace
3. Considerar el archivo docker-compose-hadoop.yml
4. Ejecutar el siguiente comando para desplegar los contenedores<br>
```    >_ docker-compose up     ``` <br>

#### PRACTICA 1 ## 

1 Descargar un set de datos en formato csv

2 crear base de datos en mysql de su preferencia

3 Importar la base de datos con la herramienta adminer


### Error al subir cambios

$ git add . && git commit -m "update" && git push origin master
error: failed to push some refs to 'https://github.com/vanessagirondaaquize/BigDataArquitecturaV2'
hint: Updates were rejected because the remote contains work that you do not
hint: have locally. This is usually caused by another repository pushing to
hint: the same ref. If you want to integrate the remote changes, use
hint: 'git pull' before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
$ git push origin master --force
$ git add . && git commit -m "update" && git push origin master
