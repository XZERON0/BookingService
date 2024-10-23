package project.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User customer;

    @OneToOne
    private Provider provider;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public Provider getProvider() {
        return provider;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
