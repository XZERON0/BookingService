package project.backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true) // Обратите внимание на "provider"
    private List<SubServiceCategory> subServiceCategories;

    // Другие поля и методы...

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SubServiceCategory> getSubServiceCategories() {
        return subServiceCategories;
    }

    public void setSubServiceCategories(List<SubServiceCategory> subServiceCategories) {
        this.subServiceCategories = subServiceCategories;
    }
}
