FROM openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} parser-service.jar
COPY application-prod.yml application-prod.yml
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "parser-service.jar"]