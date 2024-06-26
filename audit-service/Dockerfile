FROM amazoncorretto:21 AS build
WORKDIR /app
RUN yum install -y maven
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM amazoncorretto:21
RUN mkdir -p /reports_xls
COPY --from=build /app/target/*.jar audit-service.jar
ENTRYPOINT ["java","-jar","/audit-service.jar"]
