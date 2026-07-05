# Use Java 17 runtime image as base image
FROM eclipse-temurin:17-jre

# Set working directory inside container
WORKDIR /app

# Copy Spring Boot jar file from target folder into container as app.jar
COPY target/*.jar app.jar

# Spring Boot application usually runs on port 8080
EXPOSE 8080

# Run Spring Boot jar when container starts
ENTRYPOINT ["java", "-jar", "app.jar"]