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

import project.backend.models.Provider;
import project.backend.repository.ProviderRepository;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderRepository rep;
    
    @GetMapping
    public ResponseEntity<List<Provider>> allProvider()
    {
        List<Provider> items = rep.findAll();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Provider> post(@RequestBody Provider provider)
    {
        return ResponseEntity.ok(rep.save(provider));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Provider> get(@PathVariable long id)
    {
        return ResponseEntity.ok(rep.findById(id).orElseThrow());
    }
    
}
