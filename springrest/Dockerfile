FROM openjdk:21-jdk
# Set working directory inside the container
WORKDIR /app

WORKDIR /app
  COPY target/*.jar /app/app.jar
  ENTRYPOINT ["java", "-jar", "app.jar"]