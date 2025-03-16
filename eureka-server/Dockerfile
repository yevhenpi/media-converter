FROM ubuntu:latest
LABEL authors="euji"
# Use an official OpenJDK runtime as a base image
FROM openjdk:21-jdk-slim
RUN apt-get update && apt-get install -y curl

# Set the working directory inside the container
WORKDIR /eureka-server

# Copy the compiled JAR file into the container
COPY target/*.jar eureka-server.jar

# Expose the Eureka Server port
EXPOSE 8761

# Run the Eureka Server
CMD ["java", "-jar", "/eureka-server/eureka-server.jar"]


