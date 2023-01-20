FROM bellsoft/liberica-openjdk-alpine:14
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} email-rest-service.jar
ENTRYPOINT ["java","-jar","/email-rest-service.jar"]
