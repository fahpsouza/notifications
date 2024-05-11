package br.com.mercadolivre.notifications.repository;

import br.com.mercadolivre.notifications.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserName(String userName);

    List<Notification> findAll();

}
