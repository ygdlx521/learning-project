FROM ubuntu:latest
MAINTAINER daliang "heyunliang@yonet.tech"


ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV JRE_HOME /usr/lib/jvm/java-8-openjdk-amd64/jre

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk openjdk-8-jre openjdk-8-jdk-headless && \
    apt-get update && \
    apt-get clean &&\
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*



