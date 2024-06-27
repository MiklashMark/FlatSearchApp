FROM amazoncorretto:11
ARG JAR_FILE=target/*.jar
WORKDIR /app
COPY $JAR_FILE audit-service.jar
CMD ["java", "-jar", "audit-service.jar"]