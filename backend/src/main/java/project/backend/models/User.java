package project.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    private String name;
    private String role;

    //get and set
    public long getId(){return this.id;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public String getName(){return this.name;}
    public String getRole(){return this.role;}

    public void setId(long id){this.id = id;}
    public void setEmail(String email){this.email = email;}
    public void setPassword(String password){this.password = password;}
    public void setName(String name){this.name = name;}
    public void setRole(String role){this.role = role;}

}
