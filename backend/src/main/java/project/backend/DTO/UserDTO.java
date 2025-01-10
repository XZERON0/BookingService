package project.backend.DTO;

import java.time.LocalDateTime;

public class UserDTO {
    private long id;
    private String email;
    private String name;
    private String physicalAddress;
    private String avatar;
    private boolean isOnline;
    private LocalDateTime lastOnline;
    private LocalDateTime createdAt;
    private UserRole role;

    public enum UserRole {
        USER, ADMIN, MODERATOR;
    }

    public UserDTO() {}

    public UserDTO(long id, String email, String name, String physicalAddress, String avatar, boolean isOnline, LocalDateTime lastOnline, LocalDateTime createdAt, UserRole role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.physicalAddress = physicalAddress;
        this.avatar = avatar;
        this.isOnline = isOnline;
        this.lastOnline = lastOnline;
        this.createdAt = createdAt;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public LocalDateTime getLastOnline() {
        return lastOnline;
    }

    public void setLastOnline(LocalDateTime lastOnline) {
        this.lastOnline = lastOnline;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}