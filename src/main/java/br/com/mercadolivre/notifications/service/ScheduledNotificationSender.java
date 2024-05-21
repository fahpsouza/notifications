package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduledNotificationSender {

    private final NotificationService notificationService;

    public ScheduledNotificationSender(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 60000) // Envia notificações a cada 60 segundos
    public void sendGroupedNotifications() {
        List<Notification> notificationsList = notificationService.getAllNotificationsPending();
        System.out.println("Quantidade de notificações Pendentes: " + notificationsList.size());
        if (!notificationsList.isEmpty()) {
            for (Notification notification : notificationsList) {
                if (!sendNotification(notification)){
                    break;
                }
            }
        } else {
            System.out.println("Lista vazia!");
        }

    }

    private Boolean sendNotification(Notification notification) {
        int responseCode = notificationService.sendNotificationNew(createDtoNotification(notification));
        if (responseCode == HttpStatus.CREATED.value()){
            notification.setStatus("SENT");
            notification.setSentTime(LocalDateTime.now());
            notificationService.saveNotification(notification);
            return true;
        }

        return false;
    }

    private SendNotificationToWebfluxDto createDtoNotification(Notification notification){
        SendNotificationToWebfluxDto dto = new SendNotificationToWebfluxDto(
                notification.getMessage(),
                notification.getNotificationType(),
                notification.getId());
        return dto;
    }

}