package project.backend.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.backend.models.User;
import project.backend.repository.UserReposity;
import project.backend.service.JwtService;
import project.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserReposity userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
   @GetMapping("/current")
public ResponseEntity<?> getProfile() {
    return userService.getCurrentUser()
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(403).build());
}
    

    @PostMapping("/register")

    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Пользователь с таким email уже существует");
        }
        userService.registerUser(user);
        String token = jwtService.generateToken(existingUser);
        System.out.println(token);
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Вместо "Ok" возвращаем сам токен
        return ResponseEntity.ok(token);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {
            boolean isPasswordMatch = userService.checkPassword(loginRequest.getPassword(), user.get().getPassword());
            if (isPasswordMatch) {
                String token = jwtService.generateToken(user);
                System.out.println(token);
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Вместо "Ok" возвращаем сам токен
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.badRequest().body("Неверный пароль");
            }
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public Page<User> getAllUsers
    (@RequestParam(defaultValue="0") int page,  
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending )
    {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userService.getAllUser(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
    Optional<User> existingUser = userRepository.findById(id);
    if (existingUser.isPresent()) {
        existingUser.get().setEmail(user.getEmail());
        existingUser.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(existingUser.get());
        return ResponseEntity.ok("Пользователь успешно обновлен");
    } else {
        return ResponseEntity.badRequest().body("Пользователь не найден");
    }
}
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return ResponseEntity.ok("Пользователь успешно удален");
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }
}
