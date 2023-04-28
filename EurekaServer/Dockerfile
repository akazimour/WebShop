FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} eurekaServer.jar

ENTRYPOINT ["java", "-jar","/eurekaServer.jar"]

EXPOSE 8761

