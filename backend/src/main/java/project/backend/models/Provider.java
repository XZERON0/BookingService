package project.backend.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<ProviderService> providerServices;
    
    public enum ProviderSub
    {
        basic, premium;
    }

    @OneToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private ProviderSub subscription = ProviderSub.basic;
    // Другие поля и методы...

    public ProviderSub getSubscription()
    {
        return this.subscription;
    }
    public void setSubscription(ProviderSub subscription)
    {
        this.subscription = subscription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public User getUser()
    {
        return this.user;
    }
    public void setUser(User user)
    {
        this.user = user;
    }
    public List<ProviderService> setProviderServices() {
        return providerServices;
    }

    public void setProviderServices(List<ProviderService> providerServices) {
        this.providerServices = providerServices;
    }
}
