package project.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String status;

    public long getId()
    {
        return id;
    }
    public String getStatus(){return status;}

    public void setId(long id)
    {
        this.id = id;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
}
