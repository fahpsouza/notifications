package br.com.mercadolivre.notifications.consumer;

import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationConsumer {
//
//    @RabbitListener(queues = "sendNotificationsToWebfluxApi")
//    public void processMessage(Notification notification) {
//        // Lógica de processamento da mensagem recebida do RabbitMQ
//        System.out.println("Mensagem recebida: " + notification.getMessage());
//
//        String apiUrl = "https://api.example.com/send-notification";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        // Criação do corpo da requisição
//        HttpEntity<String> request = new HttpEntity<>(notification.getMessage(), headers);
//
//        try {
//            ResponseEntity<String> responseEntity = restTemplate.postForEntity(apiUrl, request, String.class);
//
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                String responseBody = responseEntity.getBody();
//                System.out.println("Resposta da API: " + responseBody);
//                // Processa e envia a notificação
//
//            } else {
//                System.err.println("Erro ao chamar a API: " + responseEntity.getStatusCodeValue());
//            }
//        } catch (Exception e) {
//            System.err.println("Exceção ao chamar a API: " + e.getMessage());
//        }
//    }

}

