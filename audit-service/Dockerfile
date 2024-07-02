FROM openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} audit-service.jar
COPY application-prod.yml application-prod.yml
RUN mkdir -p /reports
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "audit-service.jar"]