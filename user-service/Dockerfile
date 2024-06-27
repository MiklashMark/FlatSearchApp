FROM amazoncorretto:11
ARG JAR_FILE=/home/runner/work/FlatSearchApp/FlatSearchApp/user-service/target/user-service.jar
WORKDIR /app
COPY $JAR_FILE user-service.jar
CMD ["java", "-jar", "user-service.jar"]