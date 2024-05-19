package br.com.mercadolivre.notifications.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String name ;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    private Boolean acceptReceivingNotifications = true;

    public User(String name, String email, Boolean acceptReceivingNotifications) {
        this.name = name;
        this.email = email;
        this.acceptReceivingNotifications = acceptReceivingNotifications;
    }

    public void unsubscribeNotification() {
        setAcceptReceivingNotifications(false);
    }
}
