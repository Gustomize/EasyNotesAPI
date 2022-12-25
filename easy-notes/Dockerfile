FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -Dspring.datasource.username=${DB_USER} -Dspring.datasource.password=${DB_PASSWORD} -jar /app.jar"]