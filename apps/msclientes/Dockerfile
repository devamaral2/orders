#
# BUILD STAGE
#
FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests


#
# PACKAGE STAGE
#
FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar runner.jar
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod -Duser.timezone=UTC -jar runner.jar"]

