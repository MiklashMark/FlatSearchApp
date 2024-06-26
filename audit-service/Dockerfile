# Используем базовый образ Amazon Corretto для сборки
FROM amazoncorretto:21 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Устанавливаем Maven
RUN yum install -y maven

# Копируем pom.xml и src для сборки
COPY pom.xml .
COPY src ./src

# Выполняем команду сборки проекта
RUN mvn clean package

# Создаем финальный образ с Amazon Corretto
FROM amazoncorretto:21

# Создаем директорию для отчетов
RUN mkdir -p /reports_xls

# Копируем собранный JAR-файл из предыдущего этапа
COPY --from=build /app/target/*.jar audit-service.jar

# Устанавливаем точку входа
ENTRYPOINT ["java","-jar","/audit-service.jar"]
