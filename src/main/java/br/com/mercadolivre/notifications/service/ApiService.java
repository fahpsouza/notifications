package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public HttpResponse<SendNotificationToWebfluxDto> dispararRequestPost() {

        SendNotificationToWebfluxDto responseBody = null;
        try {
            // URL da API que você deseja consumir
            String apiUrl = "http://localhost:8352/notification/receipt";

            // Criação do cabeçalho HTTP com o tipo de mídia JSON
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Criação do corpo da requisição
            HttpEntity<String> request = new HttpEntity<>(headers);

            // Chamada POST para a API usando RestTemplate
            ResponseEntity<SendNotificationToWebfluxDto> responseEntity = restTemplate.postForEntity(apiUrl, request, SendNotificationToWebfluxDto.class);

            // Verifica o código de status da resposta
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                responseBody = responseEntity.getBody();
                System.out.println("Resposta da API: " + responseBody);
            } else {
                System.err.println("Erro ao chamar a API: " + responseEntity.getStatusCodeValue());
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro calling APIS:" + e.getMessage());

        }
        return (HttpResponse<SendNotificationToWebfluxDto>) responseBody;
    }
}
