package project.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.backend.models.User;
import project.backend.repository.UserReposity;

@Service
public class UserService {

    @Autowired
    private UserReposity userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); 
        return userRepository.save(user);
    }

    public boolean checkPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword); 
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


public Page<User> getAllUser(Pageable pageable, String search) {
    if (search == null || search.isBlank())
    {
        return userRepository.findAll(pageable);
        
    }
    return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);
}
    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
             
            // Проверка, что principal - это строка (email)
            if (principal instanceof String) {
                String username = (String) principal;  // email пользователя
                return userRepository.findByEmail(username);  // Получаем пользователя по email
            }
        }
        return Optional.empty();
    }
    

}
