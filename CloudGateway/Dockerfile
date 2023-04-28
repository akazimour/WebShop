FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} cloudGateway.jar

ENTRYPOINT ["java", "-jar","/cloudGateway.jar"]

EXPOSE 9090

