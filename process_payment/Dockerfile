FROM maven:3.8.3-jdk-11 AS build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests


FROM openjdk:17-jre-slim
ENV SPRING_PROFILES_ACTIVE=production
COPY --from=build /app/target/my-application.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]