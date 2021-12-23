#!/bin/sh
mvn clean package && docker build -t com.mycompany/ProyectoFinalJavaCaC .
docker rm -f ProyectoFinalJavaCaC || true && docker run -d -p 9080:9080 -p 9443:9443 --name ProyectoFinalJavaCaC com.mycompany/ProyectoFinalJavaCaC