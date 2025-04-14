# Build stage
FROM gradle:8.4.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# Package stage
#COMENTARIO PRUEBA
FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
