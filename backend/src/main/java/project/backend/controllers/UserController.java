package project.backend.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

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
    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAvatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
        }
        try {
            // Check if the directory exists, if not, create it
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
    
            byte[] bytes = file.getBytes();
            Path path = uploadDir.resolve(file.getOriginalFilename());
            Files.write(path, bytes);
    
            // Обновляем путь к аватарке в базе данных
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                String avatarUrl = "/uploads/" + file.getOriginalFilename();
                user.setAvatar(avatarUrl);
                userRepository.save(user);
            }
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + path.toString());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
    

    @GetMapping("/current")
    public ResponseEntity<?> getProfile() {
    return userService.getCurrentUser()
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(403).build());
}
@PostMapping("/register")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser.isPresent()) {
        return ResponseEntity.badRequest().body("Пользователь с таким email уже существует");
    }
    if (user.getPassword() == null || user.getPassword().isEmpty()) {
        return ResponseEntity.badRequest().body("Пароль не может быть пустым");
    }
    if (user.getEmail() == null || user.getEmail().isEmpty()) {
        return ResponseEntity.badRequest().body("Email не может быть пустым");
    }
    userService.registerUser(user);
    return ResponseEntity.ok(user);
}
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {
            boolean isPasswordMatch = userService.checkPassword(loginRequest.getPassword(), user.get().getPassword());
            if (isPasswordMatch) {
                String token = jwtService.generateAccessToken(user.get().getEmail());
                String refreshToken = jwtService.generateAccessToken(user.get().getEmail()); 
                System.out.println(token);
                Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // Вместо "Ok" возвращаем сам токен
                Map<String, Object> response = new HashMap<>();
                response.put("token", token);
                response.put("refreshToken", refreshToken);

                response.put("user", user);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body("Неверный пароль");
            }
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
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
    @RequestParam(defaultValue = "true") boolean ascending, 
    @RequestParam(defaultValue="") String filterBy,
    @RequestParam(defaultValue="") String search)
    { 
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userService.getAllUser(pageable,search);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody User user) {
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.delete(existingUser.get());
            return ResponseEntity.ok("Пользователь успешно удален");
        } else {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }
}
