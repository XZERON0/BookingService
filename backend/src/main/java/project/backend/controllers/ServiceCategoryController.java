package project.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    private ServiceCategoryRepository rep;

    @Transactional    
    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategory> getServiceCategory(@PathVariable long id) {
        ServiceCategory serviceCategory = rep.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Категория не найдена"));
        return ResponseEntity.ok(serviceCategory);
    }

    @Transactional    
    @GetMapping
    public ResponseEntity<List<ServiceCategory>> getAllServiceCategory()
    {
        List<ServiceCategory> categories = rep.findAll();
        return ResponseEntity.ok(categories);
    }
    @Transactional
    @PostMapping
    public ResponseEntity<String> postCategory(@RequestBody ServiceCategory serviceCategory)
    {
        rep.save(serviceCategory);
        return ResponseEntity.ok("Success");
    }
}