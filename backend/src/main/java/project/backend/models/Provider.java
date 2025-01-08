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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

}
