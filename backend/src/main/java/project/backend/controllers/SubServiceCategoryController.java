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

import project.backend.models.SubServiceCategory;
import project.backend.repository.SubServiceCategoryRepository;

@RestController
@RequestMapping("/service/sub")
public class SubServiceCategoryController {

    @Autowired
    private SubServiceCategoryRepository rep;
    @PostMapping
    public ResponseEntity<String> createSubServiceCategory(@RequestBody SubServiceCategory request) {
        rep.save(request);
        return ResponseEntity.ok("Success");
    }
    @GetMapping
    public ResponseEntity<List<SubServiceCategory>> allSub()
    {
        List<SubServiceCategory> sub = rep.findAll();
        return ResponseEntity.ok(sub);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubServiceCategory> getSubServiceCategory(@PathVariable long id)
    {
        SubServiceCategory el = rep.findById(id)
        .orElseThrow();
        return ResponseEntity.ok(el);
    }
    
}
