FROM amazoncorretto:21

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} parser-service.jar

ENTRYPOINT ["java","-jar","/parser-service.jar"]