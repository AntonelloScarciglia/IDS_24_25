package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GESTORE")
public class Gestore extends UtenteVerificato{

    // JPA
    protected Gestore(){}

    public Gestore(String nome, String cognome, String email, String password) {
        super(nome, cognome, email, password);
    }

}
