package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import br.com.mercadolivre.notifications.model.Notification;
import br.com.mercadolivre.notifications.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ScheduledNotificationSender scheduledNotificationSender;

    public NotificationService(NotificationRepository repository, RabbitTemplate rabbitTemplate) {
        this.notificationRepository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createNotification(ReceiveNotificationDto dto) {
        notificationRepository.save(new Notification(dto));
    }

    public void createNotificationBean(Notification notification) {
        notification.setStatus("PENDING");
        notificationRepository.save(notification);
    }

    public void sendNotification(Notification notification) throws JsonProcessingException {
        notificationRepository.updateNotification(notification.getId(), notification.getMessage(), notification.getUserName(), notification.getEmail(), "SENT");
        rabbitTemplate.convertAndSend("notificationQueue", notificationToJson(notification));
    }

    public List<Notification> getAllNotificationsPending() {
        return notificationRepository.findByScheduledTimeBeforeAndStatus(LocalDateTime.now(), "PENDING");
    }

    public List<Notification> getPendingNotifications() {
        return notificationRepository.findByStatus("PENDING");
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Transactional
    public void updateNotification(Notification notification) {
        Long id = notification.getId();
        int updatedRows = notificationRepository.updateNotification(
                notification.getId(), notification.getMessage(), notification.getUserName(), notification.getEmail(), notification.getStatus());
        if (updatedRows == 0) {
            throw new IllegalArgumentException("No Notification found with id: " + id);
        }
    }


    public void updateNotification(Long id, String message, String userName, String email, String status) {
        int updatedRows = notificationRepository.updateNotification(id, message, userName, email, status);
        if (updatedRows == 0) {
            throw new IllegalArgumentException("No Notification found with id: " + id);
        }
    }

    private String notificationToJson(Notification notification) throws JsonProcessingException {
        // You can directly autowire the converter instead of creating a new instance if it's already configured
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter.getObjectMapper().writeValueAsString(notification);
    }


}
