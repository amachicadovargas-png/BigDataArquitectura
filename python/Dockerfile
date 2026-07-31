FROM python:3.7

RUN pip install --no-cache-dir \
    jupyterlab \
    pyspark==2.4.0 \
    mysql-connector-python \
    psycopg2-binary \
    pymongo

# Install OpenJDK-8
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk

ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

#RUN apt-get -y install default-jre

WORKDIR /user/app

# Crear carpeta para jars
RUN mkdir -p /opt/spark/jars

# Copiar conector JDBC MySQL
COPY jars/mysql-connector-j-8.4.0.jar /opt/spark/jars/

WORKDIR /user/app

<<<<<<< HEAD
RUN pip install pyspark==3.0.3

RUN mkdir -p /opt/java8 && \
    curl -L https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u422-b05/OpenJDK8U-jdk_x64_linux_hotspot_8u422b05.tar.gz -o /tmp/jdk8.tar.gz && \
    tar -xzf /tmp/jdk8.tar.gz -C /opt/java8 --strip-components=1 && \
    rm /tmp/jdk8.tar.gz

ENV JAVA_HOME=/opt/java8
ENV PATH="$JAVA_HOME/bin:$PATH"
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
