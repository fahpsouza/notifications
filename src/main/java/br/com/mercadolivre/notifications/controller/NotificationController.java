package br.com.mercadolivre.notifications.controller;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import br.com.mercadolivre.notifications.model.Notification;
import br.com.mercadolivre.notifications.service.NotificationService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/isAlive")
    public ResponseEntity<String> isAlive() {
        try {
            return ResponseEntity.ok("The application is UP and Running! Local");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/send")
    @Transactional
    public Notification receive(@RequestBody @Valid ReceiveNotificationDto dto) {
        return this.notificationService.createNotification(dto);
    }
}