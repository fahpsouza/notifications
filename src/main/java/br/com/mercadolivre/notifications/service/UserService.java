package br.com.mercadolivre.notifications.service;

import br.com.mercadolivre.notifications.dto.UserCreateDto;
import br.com.mercadolivre.notifications.model.User;
import br.com.mercadolivre.notifications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setAcceptReceivingNotifications(false);
        return userRepository.save(user);
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

    public User saveUserDto(UserCreateDto dto) {
        User user = new User();
        user.setName(dto.name().trim());
        user.setEmail(dto.email().trim());
        user.setAcceptReceivingNotifications(dto.acceptReceivingNotifications());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
