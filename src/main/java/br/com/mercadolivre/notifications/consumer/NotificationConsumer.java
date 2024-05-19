package br.com.mercadolivre.notifications.consumer;

import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @RabbitListener(queues = "sendNotificationsToWebfluxApi")
    public void receiveMessage(Notification notification) {
        // Lógica de processamento da mensagem recebida do RabbitMQ
        System.out.println("Mensagem recebida: " + message);

        // URL da API externa que você deseja chamar
        String apiUrl = "https://api.example.com/send-notification";

        // Criação do cabeçalho HTTP com o tipo de mídia JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Criação do corpo da requisição
        HttpEntity<String> request = new HttpEntity<>(message, headers);


        try {
            // Chamada POST para a API externa usando RestTemplate
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, request, String.class);

            // Verifica o código de status da resposta
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();
                System.out.println("Resposta da API: " + responseBody);
                // Processa e envia a notificação

            } else {
                System.err.println("Erro ao chamar a API: " + responseEntity.getStatusCodeValue());
            }
        } catch (Exception e) {
            System.err.println("Exceção ao chamar a API: " + e.getMessage());
        }
    }

}

