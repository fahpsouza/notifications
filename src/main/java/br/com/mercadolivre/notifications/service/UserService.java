package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.model.User;
import br.com.mercadolivre.notifications.repository.UserRepository;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findUserByName(String name) {
        try {
            User existsUser = userRepository.findUserByNameEqualsIgnoreCase(name.trim());
            Objects.requireNonNull(existsUser, "O objeto não pode ser nulo.");
            existsUser.setAcceptReceivingNotifications(false);
            userRepository.save(existsUser);
            return existsUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User saveUser(User user) {
        user.setName(user.getName().trim());
        user.setEmail(user.getEmail().trim());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
