# Use an official Maven image to build the project
FROM maven:3.8.7-openjdk-11 AS build

# Set working directory inside the container
WORKDIR /app

# Copy all project files
COPY . .

# Build the project and package it as a JAR
RUN mvn clean package -DskipTests

# Use a lightweight Java runtime to run the app
FROM openjdk:11-jre-slim

# Set working directory for runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/Course-registration-0.0.1-SNAPSHOT.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]