package project.backend.models;

import jakarta.persistence.*;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "sub_service_category_id", nullable = false)
    private SubServiceCategory subServiceCategory;

    @Column(length = 250)
    private String aboutMe;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public SubServiceCategory getSubServiceCategory() {
        return subServiceCategory;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSubServiceCategory(SubServiceCategory subServiceCategory) {
        this.subServiceCategory = subServiceCategory;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
