FROM openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} audit-service.jar
CMD ["java", "-jar", "audit-service.jar"]