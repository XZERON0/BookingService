package project.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name; 
    @Column(nullable=true)
    private String physicalAddress;
    @Column(nullable = true)
    private String avatar;  // Аватар user
    
    @Column(nullable = true)
    private boolean isOnline; // Статус пользователя (онлайн/офлайн)

    public enum UserRole
    {
        USER, ADMIN, MODERATOR;
    }
    @Column(nullable=true)
    private LocalDateTime lastOnline; // Время последнего визита

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Дата создания

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private Provider provider;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    // Конструктор для автоматической установки createdAt

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
