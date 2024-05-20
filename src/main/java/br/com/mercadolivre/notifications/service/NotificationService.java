package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import br.com.mercadolivre.notifications.model.Notification;
import br.com.mercadolivre.notifications.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository repository, RabbitTemplate rabbitTemplate) {
        this.notificationRepository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Notification createNotification(ReceiveNotificationDto dto) {
        return notificationRepository.save(new Notification(dto));
    }


    public List<Notification> getAllNotificationsPending() {
        return notificationRepository.findByScheduledTimeBeforeAndStatus(LocalDateTime.now(), "PENDING");
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Transactional
    public void updateNotification(Long id) {
        int updatedRows = notificationRepository.updateNotification(id, "SENT");
        if (updatedRows == 0) {
            throw new IllegalArgumentException("No Notification found with id: " + id);
        }
    }


    private String notificationToJson(Notification notification) throws JsonProcessingException {
        // You can directly autowire the converter instead of creating a new instance if it's already configured
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter.getObjectMapper().writeValueAsString(notification);
    }

    public void markAsSent(Long id) {
        // Aqui você implementa a lógica para marcar uma notificação como enviada
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        notification.setStatus("SENT"); // Supondo que há um campo 'sent' na entidade Notification
        notificationRepository.save(notification);
    }

    public void saveNotification(Notification notification) {
        notificationRepository.save(notification);
    }


    public void sendNotificationNew(SendNotificationToWebfluxDto dto) {
        try {
            // Criação da URL
            String apiUrl = "http://localhost:8352/notification/receipt";
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true); // Necessário para enviar o corpo da solicitação
            Gson gson = new Gson();
            String jsonString = gson.toJson(dto);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Verificando o código de resposta
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Lendo a resposta da API
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println("Response: " + content.toString());
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
//                Notification notification = notificationRepository.findById(dto)
            } else {
                System.out.println("POST request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
