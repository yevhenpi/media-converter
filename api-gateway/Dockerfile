FROM ubuntu:latest
LABEL authors="euji"
# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /api-gateway

# Copy the compiled JAR file into the container
COPY target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar


EXPOSE 8080


CMD ["java", "-jar", "/api-gateway/api-gateway.jar"]
