
package project.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.backend.models.Provider;
import project.backend.repository.ProviderRepository;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Autowired
    private ProviderRepository rep;
    
     @GetMapping
    public ResponseEntity<?> allProvider(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            Optional<Provider> provider = rep.findByUserId(userId);
            if (provider.isPresent()) {
                return ResponseEntity.ok(List.of(provider.get()));
            } 
        }
        List<Provider> items = rep.findAll();
        if (items.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Нету данных");
            }

        return ResponseEntity.ok(items);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkUserIsProvider(@RequestParam long id)
    {
        Provider provider = rep.findById(id).orElse(null);
        if (provider != null) {
            return ResponseEntity.ok(true);
            }
            
        return null;
    }

    @PutMapping("/{id}")
public ResponseEntity<?> updateProvider(@PathVariable long id,@RequestBody Provider p)
{
    Optional<Provider> providerOpt = rep.findById(id);
    if (providerOpt.isPresent()) {
        Provider provider = providerOpt.get();
        provider.setProviderServices(p.getProviderServices()); 
        provider.setSubscription(p.getSubscription());
        provider.setUser(p.getUser());
    
        return ResponseEntity.ok(rep.save(provider));
    }
    return ResponseEntity.notFound().build();
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
