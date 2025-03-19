FROM ubuntu:latest
LABEL authors="yevhenpi"
# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /core

# Copy the compiled JAR file into the container
COPY target/*.jar core.jar

# Run the Eureka Server
CMD ["java", "-jar", "/core/core.jar"]


