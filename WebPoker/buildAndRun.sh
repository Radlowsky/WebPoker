#!/bin/sh
mvn clean package && docker build -t com.mycompany/WebPoker .
docker rm -f WebPoker || true && docker run -d -p 9080:9080 -p 9443:9443 --name WebPoker com.mycompany/WebPoker