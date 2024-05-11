package br.com.mercadolivre.notifications.repository;

import br.com.mercadolivre.notifications.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByName(String name);

    User findUserByNameEqualsIgnoreCase(String name);

}
