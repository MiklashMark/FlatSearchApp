FROM openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} user-service.jar
CMD ["java", "-jar", "user-service.jar"]