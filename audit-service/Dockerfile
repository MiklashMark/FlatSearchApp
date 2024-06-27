FROM amazoncorretto:11
ARG JAR_FILE=/home/runner/work/FlatSearchApp/FlatSearchApp/audit-service/target/audit-service.jar
WORKDIR /app
COPY $JAR_FILE audit-service.jar
CMD ["java", "-jar", "audit-service.jar"]