FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} paymentService.jar

ENTRYPOINT ["java", "-jar","/paymentService.jar"]

EXPOSE 8083

