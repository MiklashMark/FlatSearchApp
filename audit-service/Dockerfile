FROM amazoncorretto:21
RUN mkdir -p /reports_xls
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} audit-service.jar

ENTRYPOINT ["java","-jar","/audit-service.jar"]