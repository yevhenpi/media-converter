FROM ubuntu:latest
LABEL authors="yevhenpi"
# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /api-gateway

# Copy the compiled JAR file into the container
COPY target/*.jar api-gateway.jar



CMD ["java", "-jar", "/api-gateway/api-gateway.jar"]
