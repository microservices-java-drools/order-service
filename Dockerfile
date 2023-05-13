FROM openjdk:17-alpine
VOLUME /tmp
ADD target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]