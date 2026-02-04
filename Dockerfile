FROM amazoncorretto:17

WORKDIR /app

COPY target/*.war app.war

EXPOSE 8080

ENTRYPOINT ["java","-war","app.war"]
