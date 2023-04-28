FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} orderService.jar

ENTRYPOINT ["java", "-jar","/orderService.jar"]

EXPOSE 8082

