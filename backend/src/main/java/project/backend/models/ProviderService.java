package project.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProviderService {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "sub_service_category_id", nullable = false)
    private SubServiceCategory subServiceCategory;


    // Например, поле доступности или описание
    @Column(nullable = true)
    private String description;
    
    public long getId(){return id;}
    public Provider getProvider(){return provider;}
    public SubServiceCategory getSubServiceCategory(){return subServiceCategory;}
    public String getDescription(){return description;}
    
    public void setId(long id){this.id = id;}
    public void setProvider(Provider provider){this.provider = provider;}
    public void setSubServiceCategory(SubServiceCategory subServiceCategory){this.subServiceCategory = subServiceCategory;}
    public void setDescription(String description){this.description= description;}




}
