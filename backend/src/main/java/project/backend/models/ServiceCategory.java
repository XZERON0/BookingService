package project.backend.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"subCategories"})
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    private List<SubServiceCategory> subCategories;

    // геттеры и сеттеры
    public long getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public List<SubServiceCategory> getSubCategories() {
        return this.subCategories;
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

