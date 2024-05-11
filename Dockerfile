FROM openjdk:17-slim
RUN apt-get update && apt-get -y install netcat && apt-get clean
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]