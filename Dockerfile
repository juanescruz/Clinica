#
# Build stage
#
FROM gradle:latest AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN curl -L https://services.gradle.org/distributions/gradle-6.5.1-bin.zip -o gradle-6.5.1-bin.zip
RUN unzip gradle-6.5.1-bin.zip
ENV GRADLE_HOME=/app/gradle-6.5.1
ENV PATH=$PATH:$GRADLE_HOME/bin
RUN gradle clean
RUN gradle bootJar
#
# Package stage
#
FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=build/libs/*.jar
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
EXPOSE ${PORT} 
ENTRYPOINT ["java", "-jar", "/app.jar"]

