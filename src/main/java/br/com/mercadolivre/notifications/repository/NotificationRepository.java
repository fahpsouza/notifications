package br.com.mercadolivre.notifications.repository;

import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAll();

    List<Notification> findByEmailAndScheduledTimeBeforeAndStatus(String recipient, LocalDateTime beforeTime, String status);

    List<Notification> findByStatus(String status);

    List<Notification> findByScheduledTimeBeforeAndStatus(LocalDateTime beforeTime, String status);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.message = :message, n.userName = :userName, n.email = :email, n.status = :status WHERE n.id = :id")
    int updateNotification(Long id, String message, String userName, String email, String status);

}
