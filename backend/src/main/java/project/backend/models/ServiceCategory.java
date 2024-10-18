package project.backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String type;

    // Один ко многим: одна категория может иметь множество подкатегорий
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubServiceCategory> subCategories;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<SubServiceCategory> getSubCategories() {
        return subCategories;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubCategories(List<SubServiceCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
