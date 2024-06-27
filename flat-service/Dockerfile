FROM amazoncorretto:11
ARG JAR_FILE=/home/runner/work/FlatSearchApp/FlatSearchApp/flat-service/target/flat-service.jar
WORKDIR /app
COPY $JAR_FILE flat-service.jar
CMD ["java", "-jar", "flat-service.jar"]