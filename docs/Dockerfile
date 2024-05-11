
# Usar uma base de imagem oficial do Java JDK 11 (ou a que preferir, ajuste conforme sua necessidade)
FROM openjdk:11-jdk-slim

# Path dentro do container
WORKDIR /app-notifications-mercado-livre

# Argumento para passar o nome do arquivo jar (ajuste o nome conforme necessário)
ARG JAR_FILE=target/notifications-0.1.0.jar

# Argumento para passar a porta do build
ARG PORT_BUILD=8082

# Copia o arquivo jar do diretório de build para a imagem Docker
COPY ${JAR_FILE} app.jar

# Expõe a porta em que sua aplicação Spring Boot estará ouvindo
EXPOSE $PORT_BUILD

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app.jar"]
