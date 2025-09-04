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
    public void aggiorna(Notifica notifica, String messaggio) {
        if(notifica instanceof Prodotto prodotto){
            List<UtenteVerificato> utenti = utenteRepository.findAllCuratori();
            for (UtenteVerificato u : utenti) {
                addNotifica(u, "Nuovo prodotto da revisionare : " + prodotto.getNome());
            }
        }
    }
}
