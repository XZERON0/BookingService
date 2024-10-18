package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.User;

public interface UserRep extends JpaRepository<User, Long> {
}
