package br.com.mercadolivre.notifications.model;


import br.com.mercadolivre.notifications.dto.ReceiveNotificationDto;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true, length = 255)
    private String name ;

    @NotNull
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    private Boolean acceptReceivingNotifications = true;

    public User( ) {
    }

    public User(String name, String email, Boolean acceptReceivingNotifications) {
        this.name = name;
        this.email = email;
        this.acceptReceivingNotifications = acceptReceivingNotifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAcceptReceivingNotifications() {
        return acceptReceivingNotifications;
    }

    public void setAcceptReceivingNotifications(Boolean acceptReceivingNotifications) {
        this.acceptReceivingNotifications = acceptReceivingNotifications;
    }

    public void unsubscribeNotification() {
        setAcceptReceivingNotifications(false);
    }
}
