package project.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ServiceCategory")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique=true)
    private String type;

    // геттеры и сеттеры
    public long getId() {return this.id;}

    public String getType() {return this.type;}

    public void setId(long id) {this.id = id;}

    public void setType(String type) {this.type = type;}
}

