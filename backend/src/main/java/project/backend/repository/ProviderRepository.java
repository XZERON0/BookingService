package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.Provider;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}