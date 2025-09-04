package it.cs.unicam.ids.filiera.demo.observer;

import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class Observer{

    protected final UtenteRepository utenteRepository;

    protected Observer(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public abstract void aggiorna(Notifica notifica, String messaggio);

    protected void addNotifica(UtenteVerificato utente, String messaggio){
        utente.getNotifiche().add(messaggio);
        utenteRepository.save(utente);
    }
}
