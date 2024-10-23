package project.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SubServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;
    @ManyToOne
    @JoinColumn(name = "provider_id") 
    private Provider provider;
    @Column(nullable = false)
    private int servicePrice;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ServiceCategory getCategory() {
        return category;
    }


    
    public int getServicePrice() {
        return servicePrice;
    }
    public Provider getProvider(){return this.provider;}
    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }
    public void setProvider(Provider provider){this.provider = provider;}
}
