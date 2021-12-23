@echo off
call mvn clean package
call docker build -t com.mycompany/ProyectoFinalJavaCaC .
call docker rm -f ProyectoFinalJavaCaC
call docker run -d -p 9080:9080 -p 9443:9443 --name ProyectoFinalJavaCaC com.mycompany/ProyectoFinalJavaCaC