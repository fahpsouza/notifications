package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import br.com.mercadolivre.notifications.model.Notification;
import br.com.mercadolivre.notifications.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void request(ReceiveNotificationDto dto) {
        notificationRepository.save(new Notification(dto));
    }

}
