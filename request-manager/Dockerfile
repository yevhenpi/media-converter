FROM ubuntu:latest
LABEL authors="yevhenpi"
# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /request-manager

# Copy the compiled JAR file into the container
COPY target/*.jar request-manager.jar

# Run the Eureka Server
CMD ["java", "-jar", "/request-manager/request-manager.jar"]


