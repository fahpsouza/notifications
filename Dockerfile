FROM openjdk:17-slim
WORKDIR /app-notifications-mercado-livre
ARG JAR_FILE=target/notifications-0.1.1.jar
ARG PORT_BUILD=8005
COPY ${JAR_FILE} app.jar
EXPOSE $PORT_BUILD
ENTRYPOINT ["java", "-jar", "/app.jar"]