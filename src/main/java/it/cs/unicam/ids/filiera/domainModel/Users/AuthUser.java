package it.cs.unicam.ids.filiera.domainModel.Users;

import it.cs.unicam.ids.filiera.util.Role;

public class AuthUser implements User{

    private Long id;
    private String username;
    private Role role;

    public AuthUser(Long id, String username, Role role){
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public Role getRole(){
        return role;
    }


    public void showMarketplace(){
        //TODO
    }
}