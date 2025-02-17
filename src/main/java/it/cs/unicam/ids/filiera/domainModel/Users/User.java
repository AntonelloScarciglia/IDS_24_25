package it.cs.unicam.ids.filiera.domainModel.Users;

import java.util.Collections;

public class User {
    private String id;
    private String name;
    private Role role;

    public User(String id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    /**
     * Return the id of the user
     * @return id
     */
    public String getId(){
        return this.id = id;
    }

    /**
     * Return the name of the user
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Return the role of the user
     * @return role
     */
    public Role getRole(){
        return this.role;
    }

}