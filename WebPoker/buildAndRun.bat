@echo off
call mvn clean package
call docker build -t com.mycompany/WebPoker .
call docker rm -f WebPoker
call docker run -d -p 9080:9080 -p 9443:9443 --name WebPoker com.mycompany/WebPoker