FROM ubuntu:latest
MAINTAINER daliang "heyunliang@yonet.tech"


ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV JRE_HOME /usr/lib/jvm/java-8-openjdk-amd64/jre

RUN apt-get update && \
    apt-get install -y openjdk-8-jdk openjdk-8-jre openjdk-8-jdk-headless && \
    apt-get update && \
    apt-get clean &&\
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*



RUN apt-get update && apt-get install -y software-properties-common
RUN add-apt-repository ppa:deadsnakes/ppa
RUN apt-get update && apt-get install -y \
    python3.7 \
    python3-pip
RUN python3.7 -m pip install pip

RUN python3.7 -m pip install pip --upgrade pip
# Set python 3 as the default python
#RUN update-alternatives --set python /usr/bin/python3.7

COPY requirements.txt ./
RUN pip install  --no-cache-dir -r requirements.txt

RUN ln -s /usr/bin/python3 /usr/bin/python


