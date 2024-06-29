FROM  openjdk:17-oracle
ARG JAR_FILE
WORKDIR /app
COPY ${JAR_FILE} sender-service.jar
CMD ["java", "-jar", "sender-service.jar"]