FROM amazoncorretto:11
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY $JAR_FILE sender-service.jar
CMD ["java", "-jar", "sender-service.jar"]