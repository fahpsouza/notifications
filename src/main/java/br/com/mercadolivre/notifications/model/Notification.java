package br.com.mercadolivre.notifications.model;

import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import br.com.mercadolivre.notifications.util.Util;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime sendTime;
    private Boolean sent = false;
    private Long userId;
    private String userName;
    private String productDescription;
    private Long attemptsCount = 0L;

    public Notification() {
    }

    public Notification(ReceiveNotificationDto dto) {
        this.message = dto.message();
        this.userName = dto.UserName();
        this.sendTime = LocalDateTime.now();
        this.productDescription = dto.productDescription();
        this.attemptsCount = getAttemptsCount();
        this.sent = false;
        this.userId = (long) new Util().generateRandomInt(1, 1000);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public Long getUserId() {return userId;}

    public void setUserId(Long user) {
        this.userId = user;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getAttemptsCount() {return attemptsCount++;}

    public void setAttemptsCount() {this.attemptsCount = attemptsCount + 1;}

    public void setAttemptsCount(Long attemptsCount) {
        this.attemptsCount = attemptsCount;
    }

    public Boolean getSent() {return sent;}

    public void setSent(Boolean sent) {this.sent = sent;}

    public String getUserName() {return userName;}

    public void setUserName(String userName) {this.userName = userName;}



}
