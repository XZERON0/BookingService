package project.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Optional<Provider> findByUserId(Long userId);
}