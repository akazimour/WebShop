FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} productService.jar

ENTRYPOINT ["java", "-jar","/productService.jar"]

EXPOSE 8081

