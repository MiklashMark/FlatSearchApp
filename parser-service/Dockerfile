FROM amazoncorretto:11
ARG JAR_FILE=/home/runner/work/FlatSearchApp/FlatSearchApp/parser-service/target/parser-service.jar
WORKDIR /app
COPY $JAR_FILE parser-service.jar
CMD ["java", "-jar", "parser-service.jar"]