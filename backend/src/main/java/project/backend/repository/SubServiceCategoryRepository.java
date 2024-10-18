package project.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.SubServiceCategory;

public interface SubServiceCategoryRepository extends JpaRepository<SubServiceCategory, Long> {
}
