FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .

RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn -B package -DskipTests=false

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/consultacredito.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=none
ENV SPRING_KAFKA_URL=localhost:9092

EXPOSE 80

ENTRYPOINT ["java", "-jar", "app.jar"]