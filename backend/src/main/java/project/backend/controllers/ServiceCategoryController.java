package project.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<String> getServiceCategory(@PathVariable long id)
    {
        ServiceCategory serviceCategory = serviceCategoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
        return ResponseEntity.ok("IDI NAXIU");
    } 
    @PostMapping("/buuka")
    public ResponseEntity<String> atomicHeart()
    {
        return ResponseEntity.ok("Success");
    }
}