package project.backend.DTO;

import java.util.List;

public class ProviderDTO {
    private long id;
    private List<ProviderServiceDTO> providerServices;
    private UserDTO user;
    private ProviderSub subscription;

    public enum ProviderSub {
        basic, premium;
    }

    public ProviderDTO() {}

    public ProviderDTO(long id, List<ProviderServiceDTO> providerServices, UserDTO user, ProviderSub subscription) {
        this.id = id;
        this.providerServices = providerServices;
        this.user = user;
        this.subscription = subscription;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProviderServiceDTO> getProviderServices() {
        return providerServices;
    }

    public void setProviderServices(List<ProviderServiceDTO> providerServices) {
        this.providerServices = providerServices;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ProviderSub getSubscription() {
        return subscription;
    }

    public void setSubscription(ProviderSub subscription) {
        this.subscription = subscription;
    }
}