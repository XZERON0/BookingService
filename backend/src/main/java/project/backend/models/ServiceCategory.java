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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubServiceCategory> subCategories;

    @OneToMany
    private List<ServiceTitle> titles;

    // геттеры и сеттеры
    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<SubServiceCategory> getSubCategories() {
        return subCategories;
    }

    public List<ServiceTitle> getTitles() {
        return titles;
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

    public void setTitles(List<ServiceTitle> titles) {
        this.titles = titles;
    }
}
