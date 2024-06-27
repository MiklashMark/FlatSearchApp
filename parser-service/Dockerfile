FROM amazoncorretto:11
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY $JAR_FILE parser-service.jar
CMD ["java", "-jar", "parser-service.jar"]