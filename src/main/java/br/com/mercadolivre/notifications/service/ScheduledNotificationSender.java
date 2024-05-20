package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
                sendNotification(notification);
            }
        } else {
            System.out.println("Lista vazia!");
        }

    }

    private void sendNotification(Notification notification) {
        notificationService.sendNotificationNew(createDtoNotification(notification));
        notification.setStatus("SENT");
        notification.setSentTime(LocalDateTime.now());
        notificationService.saveNotification(notification);
    }

    private SendNotificationToWebfluxDto createDtoNotification(Notification notification){
        SendNotificationToWebfluxDto dto = new SendNotificationToWebfluxDto(
                notification.getMessage(),
                notification.getNotificationType(),
                notification.getId());
        return dto;
    }

}