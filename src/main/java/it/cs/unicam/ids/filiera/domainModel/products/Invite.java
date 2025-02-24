package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Event event;
    private AuthUser addressee;
    private boolean status;

    public Invite(Event event, AuthUser addressee) {
        this.event = event;
        this.addressee = addressee;
        this.status = false;
    }

    /**
     * Method to accept the invite
     */
    public void accept(){
        this.status = true;
    }

    /**
     * Method to decline the invite
     */
    public void decline(){
        this.status = false;
    }

    public Long getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public AuthUser getAddressee() {
        return addressee;
    }

    public boolean isStatus() {
        return status;
    }
}