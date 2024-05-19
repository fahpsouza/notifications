package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ScheduledNotificationSender {

    private final NotificationService notificationService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private RestTemplate restTemplate;

    public ScheduledNotificationSender(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 30000) // Executa a cada minuto
    public HttpResponse<SendNotificationToWebfluxDto> sendGroupedNotifications() {

        List<Notification> notificationsList = notificationService.getAllNotificationsPending();
        System.out.println("*** Quantidade de Notificações pendentes: " + notificationsList.size() + " ***");

        HttpResponse<SendNotificationToWebfluxDto> response = apiService.dispararRequestPost();
        return response;

//        notificationsList.stream()
//                        .(notification -> notificationService.sendNotification(notification));
//        .forEach(notification -> notificationService.sendNotification(notification));


    }
}