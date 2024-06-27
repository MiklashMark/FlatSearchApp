FROM amazoncorretto:11
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY $JAR_FILE user-service.jar
CMD ["java", "-jar", "user-service.jar"]