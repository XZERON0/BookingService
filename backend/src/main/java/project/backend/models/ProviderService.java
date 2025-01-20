package project.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class ProviderService {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    @JsonBackReference
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "sub_service_category_id", nullable = false)
    private SubServiceCategory subServiceCategory;


    // Например, поле доступности или описание
    @Column(nullable = true)
    private String description;
}
