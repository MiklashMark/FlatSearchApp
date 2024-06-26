FROM amazoncorretto:21

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sender-service.jar

ENTRYPOINT ["java","-jar","/sender-service.jar"]