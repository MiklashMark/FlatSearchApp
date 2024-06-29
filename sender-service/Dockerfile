FROM adoptopenjdk:17-jdk-hotspot
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} sender-service.jar
CMD ["java", "-jar", "sender-service.jar"]