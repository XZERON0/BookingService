package project.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.backend.models.ServiceCategory;
import project.backend.repository.ServiceCategoryRepository;

@RestController
@RequestMapping("/service")
public class ServiceCategoryController {
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getServiceCategory(@PathVariable long id) {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
        return ResponseEntity.ok(serviceCategory);
    }
    
    @PostMapping
    public ResponseEntity<ServiceCategory> createServiceCategory(@RequestBody ServiceCategory serviceCategory)
    {
        ServiceCategory createCategory = serviceCategoryRepository.save(serviceCategory);
        return ResponseEntity.ok(createCategory);
    }

    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllServiceCategory()
    {
        List<ServiceCategory> categories = serviceCategoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }
}