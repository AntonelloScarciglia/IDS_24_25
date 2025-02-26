package it.cs.unicam.ids.filiera.domainModel.Users;

import it.cs.unicam.ids.filiera.util.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuthUser implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private Role role;

    public AuthUser(Long id, String username, String email, Role role){
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;

    }

    public AuthUser() {
    }

    public String getUsername(){
        return username;
    }

    public Role getRole(){
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void showMarketplace(){
        //TODO
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}