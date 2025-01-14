package project.backend.DTO;

import java.time.LocalDateTime;

public class OrderDTO {
    private long id;
    private UserDTO customer;
    private ProviderDTO provider;
    private LocalDateTime createdAt;

    public OrderDTO() {}

    public OrderDTO(long id, UserDTO customer, ProviderDTO provider, LocalDateTime createdAt) {
        this.id = id;
        this.customer = customer;
        this.provider = provider;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public ProviderDTO getProvider() {
        return provider;
    }

    public void setProvider(ProviderDTO provider) {
        this.provider = provider;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}