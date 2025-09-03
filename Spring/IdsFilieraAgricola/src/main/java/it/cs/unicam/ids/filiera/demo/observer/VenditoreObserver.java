package it.cs.unicam.ids.filiera.demo.observer;

import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.Venditore;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VenditoreObserver extends Observer{

    protected VenditoreObserver(UtenteRepository utenteRepository) {
        super(utenteRepository);
    }

    @Override
    public void aggiorna(Prodotto prodotto, String messaggio){
        List<UtenteVerificato> utenti = utenteRepository.findAll();
        for(UtenteVerificato u : utenti){
            if(u instanceof Venditore){
                if(u.getId().equals(prodotto.getVenditoreId())){
                    addNotifica(u, messaggio);
                }
            }
        }
    }

    @Override
    public void aggiornaInv(Invito invito, String messaggio){
        List<UtenteVerificato> utenti = utenteRepository.findAll();
        for(UtenteVerificato u : utenti){
            if(u instanceof Venditore){
                if(u.getId().equals(invito.getInvitato().getId())){
                    addNotifica(u, messaggio);
                }
            }
        }
    }
}
