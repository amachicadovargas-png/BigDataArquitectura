FROM python:3.9

RUN pip install mysql-connector-python
RUN pip install jupyterlab
RUN pip install psycopg2-binary
RUN pip install pymongo

# Install OpenJDK-8
RUN apt-get update 
RUN apt-get -y install default-jre

WORKDIR /user/app


RUN pip install pyspark==3.0.3

RUN mkdir -p /opt/java8 && \
    curl -L https://github.com/adoptium/temurin8-binaries/releases/download/jdk8u422-b05/OpenJDK8U-jdk_x64_linux_hotspot_8u422b05.tar.gz -o /tmp/jdk8.tar.gz && \
    tar -xzf /tmp/jdk8.tar.gz -C /opt/java8 --strip-components=1 && \
    rm /tmp/jdk8.tar.gz

ENV JAVA_HOME=/opt/java8
ENV PATH="$JAVA_HOME/bin:$PATH"
