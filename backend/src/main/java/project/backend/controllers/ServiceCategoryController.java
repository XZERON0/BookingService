package project.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.backend.models.ServiceCategory;
import project.backend.repository.ServiceCategoryRepository;

@RestController
@RequestMapping("/service")
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @Transactional    
    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getServiceCategory(@PathVariable long id) {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
        return ResponseEntity.ok(serviceCategory);
    }
    
    @Transactional    
    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllServiceCategory()
    {
        List<ServiceCategory> categories = serviceCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }
}