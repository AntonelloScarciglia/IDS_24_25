package it.cs.unicam.ids.filiera.demo.observer;

import it.cs.unicam.ids.filiera.demo.entity.Curatore;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CuratoreObserver extends Observer{

    public CuratoreObserver(UtenteRepository utenteRepository) {
        super(utenteRepository);
    }

    @Override
    public void aggiorna(Prodotto prodotto, String messaggio){
        List<UtenteVerificato> utenti = utenteRepository.findAll();
        for(UtenteVerificato u : utenti){
            if(u instanceof Curatore){
                addNotifica(u, "Nuovo prodotto da revisionare : " + prodotto.getNome());
            }
        }
    }

    @Override
    public void aggiornaInv(Invito invito, String messaggio) {
        throw new UnsupportedOperationException("Metodo non supportato");
    }

}
