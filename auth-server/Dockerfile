FROM ubuntu:latest
LABEL authors="yevhenpi"
# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /auth-server

# Copy the compiled JAR file into the container
COPY target/*.jar auth-server.jar


CMD ["java", "-jar", "/auth-server/auth-server.jar"]
