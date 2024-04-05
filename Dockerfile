# Use official Gradle image as base
FROM gradle:latest AS build

# Set working directory
WORKDIR /home/gradle/src

# Copy Gradle project
COPY --chown=gradle:gradle . .

# Build Spring Boot application
RUN gradle clean bootJar

# Use another stage for the final image
FROM eclipse-temurin:17-jdk-jammy

# Set environment variable for port
ENV PORT=8080

# Copy JAR file from the build stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

# Expose the specified port
EXPOSE ${PORT}

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
