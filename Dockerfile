# Use another stage for the final image
FROM eclipse-temurin:17-jdk-jammy

# Copy JAR file from the build stage
COPY ./build/libs/*.jar /app/app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
