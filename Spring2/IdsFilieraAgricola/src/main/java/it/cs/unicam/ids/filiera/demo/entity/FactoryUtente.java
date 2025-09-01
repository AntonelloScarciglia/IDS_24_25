package it.cs.unicam.ids.filiera.demo.entity;

import it.cs.unicam.ids.filiera.demo.entity.*;
import it.cs.unicam.ids.filiera.demo.entity.Ruolo;

public final class FactoryUtente {

    private FactoryUtente() {} // costruttore privato

    public static UtenteVerificato createUser(Ruolo ruolo, String nome, String cognome, String email, String password,String codiceFiscale) {
        return switch (ruolo) {

            case PRODUTTORE    -> new Produttore(nome, cognome, email, password, codiceFiscale);
            case TRASFORMATORE -> new Trasformatore(nome, cognome, email, password, codiceFiscale);
            case DISTRIBUTORE  -> new Distributore(nome, cognome, email, password, codiceFiscale);
            case ACQUIRENTE    -> new Acquirente(nome, cognome, email, password);
            case ANIMATORE     -> new Animatore(nome, cognome, email, password);
        };
    }
}
