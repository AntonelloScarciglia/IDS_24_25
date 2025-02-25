package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.observer.AnimatorObserver;
import it.cs.unicam.ids.filiera.domainModel.observer.Observer;
import it.cs.unicam.ids.filiera.domainModel.observer.Subject;
import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.observer.UserObserver;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

public class Invite implements Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Event event;
    private final AuthUser addressee;
    private boolean status;
    private final List<Observer> observers;

    public Invite(Event event, AuthUser addressee) {
        this.event = event;
        this.addressee = addressee;
        this.status = false;
        this.observers = new ArrayList<>();
        this.attach(new UserObserver());
        this.notifyObservers();
    }

    /**
     * Method to accept the invite
     */
    public void accept(){
        this.status = true;
        this.observers.clear();
        this.attach(new AnimatorObserver());
        this.notifyObservers();
    }

    /**
     * Method to decline the invite
     */
    public void decline(){
        this.status = false;
        this.observers.clear();
        this.attach(new AnimatorObserver());
        this.notifyObservers();
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

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }
}