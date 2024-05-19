package br.com.mercadolivre.notifications.model;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String userName;
    private String email;
    private LocalDateTime scheduledTime;
    private Long attemptsCount = 0L;
    private String status; // PENDING, SENT, FAILED


    public Notification(ReceiveNotificationDto dto) {
        this.message = dto.message();
        this.userName = dto.userName();
        this.email = dto.email();
        this.scheduledTime = LocalDateTime.now();
        this.attemptsCount = getAttemptsCount();
        this.status = "PENDING";
    }

    public Long getAttemptsCount(){
        return attemptsCount++;
    }

}
