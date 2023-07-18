FROM openjdk:8-jre
ADD target/TrainBook-1.0.0-SNAPSHOT.war
EXPOSE 8010
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
