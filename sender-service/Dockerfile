FROM  openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} sender-service.jar
COPY application-prod.yml application-prod.yml
CMD ["java", "-Dspring.profiles.active=prod", "-jar", "sender-service.jar"]