FROM openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} audit-service.jar
COPY application.yml application.yml
COPY application-prod.yml application-prod.yml
CMD ["java", "-jar", "audit-service.jar"]