package project.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.backend.models.User;
import project.backend.repository.UserRep;
import project.backend.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRep userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким email уже существует");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {
            boolean isPasswordMatch = userService.checkPassword(loginRequest.getPassword(), user.get().getPassword());
            if (isPasswordMatch) {
                return ResponseEntity.ok("Успешный вход");
            } else {
                return ResponseEntity.badRequest().body("Неверный пароль");
            }
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
