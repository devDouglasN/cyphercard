FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ENV RABBITMQ_SERVER=rabbitmq-host
ARG EUREKA_SERVER=localhost
ARG DATABASE_SERVER=param

ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar