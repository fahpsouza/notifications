package br.com.mercadolivre.notifications.controller;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import br.com.mercadolivre.notifications.dto.SendNotificationToWebfluxDto;
import br.com.mercadolivre.notifications.service.ApiService;
import br.com.mercadolivre.notifications.service.NotificationService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("isAlive")
    public ResponseEntity<String> isAlive() {
        try {
            return ResponseEntity.ok("The application is UP and Running! Local");
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/send")
    @Transactional
    public ResponseEntity<String> receive(@RequestBody @Valid ReceiveNotificationDto dto) {
        try {
            this.notificationService.createNotification(dto);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}