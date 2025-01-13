FROM openjdk:17-jdk-alpine
LABEL authors="Dmiit3iy"
EXPOSE 8080
COPY build/libs/limit-control.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]