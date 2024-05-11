package br.com.mercadolivre.notifications.controller;

import br.com.mercadolivre.notifications.dto.UserCreateDto;
import br.com.mercadolivre.notifications.model.User;
import br.com.mercadolivre.notifications.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/create-user")
    public ResponseEntity<String> createUser(@RequestBody @Valid User user) {
        try {
            this.userService.saveUser(user);
            return ResponseEntity.ok("User register with successfully!");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@RequestBody @Valid UserCreateDto dto) {
        try {
            User user = userService.findUserByName(dto.name());
            return ResponseEntity.ok("Unsubscribing from notifications successful!");
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userService.findUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
