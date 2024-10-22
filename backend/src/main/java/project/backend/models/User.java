package project.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
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

    @Column(nullable = true)
    private String avatar; 

    @Column(nullable = true)
    private boolean isOnline; // Статус пользователя (онлайн/офлайн)

    @Column(nullable=true)
    private LocalDateTime lastOnline; // Время последнего визита

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // Дата создания

    // Конструктор для автоматической установки createdAt
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // геттеры и сеттеры
    public long getId() {
        return id;
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
