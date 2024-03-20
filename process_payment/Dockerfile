#This image is based on the popular Alpine Linux project, available in the alpine official image.
#Alpine Linux is much smaller than most distribution base images (~5MB), and thus leads to much slimmer images in general.
FROM amazoncorretto:17-alpine-jdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

CMD apt-get update -y

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]