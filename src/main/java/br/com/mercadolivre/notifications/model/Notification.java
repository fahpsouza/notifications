package br.com.mercadolivre.notifications.model;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String email;
    private LocalDateTime scheduledTime = LocalDateTime.now();
    private String notificationType; // WEB, SMS, PUSH or EMAIL
    private Long attemptsCount = 0L;
    private String status; // PENDING, SENT, FAILED
    private Long userId;
    private LocalDateTime sentTime;


    public Notification(ReceiveNotificationDto dto) {
        this.message = dto.message();
        this.email = dto.email();
        this.status = "PENDING";
        this.userId = dto.userId();
        this.notificationType = dto.notificationType();
    }

}
