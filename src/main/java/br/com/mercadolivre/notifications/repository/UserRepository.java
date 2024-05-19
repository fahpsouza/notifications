package br.com.mercadolivre.notifications.repository;

import br.com.mercadolivre.notifications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByNameEqualsIgnoreCase(String name);

}
