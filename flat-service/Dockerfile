FROM adoptopenjdk:17-jdk-hotspot
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} flat-service.jar
CMD ["java", "-jar", "flat-service.jar"]