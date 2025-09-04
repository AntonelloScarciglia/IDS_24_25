package it.cs.unicam.ids.filiera.demo.observer;

import it.cs.unicam.ids.filiera.demo.entity.Acquirente;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AcquirenteObserver extends Observer {

    public AcquirenteObserver(UtenteRepository utenteRepository){
        super(utenteRepository);
    }

    @Override
    public void aggiorna(Notifica notifica, String messaggio){
        if(notifica instanceof Prodotto prodotto){
            List<UtenteVerificato> utenti = utenteRepository.findAll();
            for(UtenteVerificato u : utenti){
                if(u instanceof Acquirente){
                    addNotifica(u, "Nuovo prodotto nel marketplace : " + prodotto.getNome());
                }
            }
        }
    }
}
