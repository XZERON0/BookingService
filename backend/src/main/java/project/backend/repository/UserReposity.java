package project.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.User;
public interface UserReposity extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
