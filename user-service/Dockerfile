FROM openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} user-service.jar
COPY application-prod.yml application-prod.yml
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "user-service.jar"]