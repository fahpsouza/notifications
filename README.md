# notifications
Criado apis que usam o SpringBoot para receber e enviar 
- notificações (api sincrona)
- controle de usuarios
- controle de JOB de execução do Envio

## Tecnologias Utilizadas
Este projeto foi desenvolvido com as seguintes tecnologias:

- Spring SpringBoot: Usado para criar o back-end.
- PostgreSQL: Banco de dados utilizado para armazenar os dados de forma eficiente e escalável.
- Dockerfile
- docker-compose.yml

## Arquitetura Proposta
[Arquitetura Proposta](https://github.com/fahpsouza/notifications/blob/main/docs/Notifications%20App.pptx)

## Fluxograma
[FLuxograma](https://github.com/fahpsouza/notifications/blob/main/docs/arquitetura_proposta.drawio)

## Pré-requisitos
Antes de começar, verifique se você possui os seguintes requisitos:
- Docker
- Docker Compose
- Instale o Postgres
- Java 17 na sua máquina. 

Veja instruções detalhadas no site oficial de 
- [Postgresql](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- [Java](https://www.java.com/en/download/help/download_options.html)

## Clone o repositório para sua máquina local:
bash
git clone https://github.com/fahpsouza/notifications.git
cd notifications-reative-webflux

##Construa o projeto com Maven:
bash
mvn clean install

##Execute o projeto:
bash
java -jar target/notifications-reative-webflux-0.0.1-SNAPSHOT.jar
O 
servidor iniciará na porta definida nas configurações.

Uso
Exemplos de como você pode utilizar as APIs criadas. Por exemplo:

http
## Para Testar importe a Collection do Json em seu Postman na raiz do projeto
- [Notifications MELI.postman_collection.json](https://github.com/fahpsouza/notifications/blob/main/Notifications%20MELI.postman_collection.json) <br><br>
![/notification](https://github.com/fahpsouza/notifications/blob/main/docs/notification_endpoints.png "/notification") <br>
![/api/users](https://github.com/fahpsouza/notifications/blob/main/docs/user_endpoints.png "/api/users") <br>

Contato
Fabio Henrique Piedade de Souza

[Linkedin](https://www.linkedin.com/in/fabio-h-p-de-souza) | <br>
[Email](fabio.henrique.psouza@gmail.com) | <br>
[Link do Projeto](https://github.com/fahpsouza/notifications)