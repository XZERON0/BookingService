package project.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="SubServiceCategory")
public class SubServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique=true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ServiceCategory category;

    // Поле для связи с поставщиком через промежуточную таблицу
    // @OneToMany(mappedBy = "subServiceCategory", cascade = CascadeType.ALL)
    // private List<ProviderService> providerServices;

    public SubServiceCategory()
    {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ServiceCategory getCategory() {
        return category;
    }
    
    // public List<ProviderService> geProviderServices() {
    //     return providerServices;
    // }
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    // public void setProviderServices(List<ProviderService> providerServices) {
    //     this.providerServices = providerServices;
    // }
}
