FROM amazoncorretto:11
ARG JAR_FILE=/home/runner/work/FlatSearchApp/FlatSearchApp/sender-service/target/sender-service.jar
WORKDIR /app
COPY $JAR_FILE sender-service.jar
CMD ["java", "-jar", "sender-service.jar"]