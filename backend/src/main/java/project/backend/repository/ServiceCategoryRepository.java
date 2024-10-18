package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.ServiceCategory;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
}
