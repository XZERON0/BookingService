package project.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.SubServiceCategory;

public interface SubServiceCategoryRepository extends JpaRepository<SubServiceCategory, Long> {

    List<SubServiceCategory> findByCategoryId(Long categoryId);
}
