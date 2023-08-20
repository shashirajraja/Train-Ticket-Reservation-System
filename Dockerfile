# Use an official Maven base image
FROM maven:3.8.1-openjdk-8 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the source code and pom.xml to the container
COPY src /app/src
COPY pom.xml /app/

# Build the application using Maven
RUN mvn clean package

# Use an official Tomcat base image
FROM tomcat:9.0.52-jdk8-openjdk

# Copy the built WAR file to the Tomcat webapps directory
COPY --from=build /app/target/TrainBook-1.0.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Expose port 8080 for Tomcat
EXPOSE 8080

# Set the command to run when the container starts
CMD ["catalina.sh", "run"]
