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

    // геттеры и сеттеры
    public UserRole getRole()
    {
        return this.role;
    }
    public void setRole(UserRole role)
    {
        this.role = role;
    }
    public long getId() {
        return id;
    }
    public Provider getProvider()
    {
        return provider;
    }
    public String getPhysicalAddress()
    {
        return physicalAddress;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public LocalDateTime getLastOnline() {
        return lastOnline;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // сеттеры для изменения данных

    public void setProvider(Provider provider)
    {
        this.provider = provider;
    }
    public void setPhysicalAddress(String physicalAddress)
    {
        this.physicalAddress = physicalAddress;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
    }
}
