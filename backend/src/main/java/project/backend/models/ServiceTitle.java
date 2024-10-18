package project.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServiceTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String title;
    // getters/setters
    public long getId(){return this.id;}
    public String getTitle(){return this.title;}
    public void setId(long id){this.id = id;}
    public void setTitle(String title){this.title = title;}


}
