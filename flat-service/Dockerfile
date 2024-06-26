FROM amazoncorretto:21

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} flat-service.jar

ENTRYPOINT ["java","-jar","/flat-service.jar"]