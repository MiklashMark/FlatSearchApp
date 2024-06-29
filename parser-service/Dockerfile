FROM adoptopenjdk:11-jdk-hotspot
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} parser-service.jar
CMD ["java", "-jar", "parser-service.jar"]