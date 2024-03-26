#criar um ambiente comn jdk e maven
#compilar aplicação
#executar comando para rodar app

FROM maven:3.8.7-openjdk-18-slim as build
RUN mkdir -p usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
RUN mvn package -DskipTests

FROM amazoncorretto:17-alpine-jdk
RUN mkdir -p usr/src/app
WORKDIR usr/src/app
COPY --from=build /usr/src/app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]