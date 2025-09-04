package it.cs.unicam.ids.filiera.demo.observer;

import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimatoreObserver extends Observer{

    protected AnimatoreObserver(UtenteRepository utenteRepository) {
        super(utenteRepository);
    }

    @Override
    public void aggiorna(Notifica notifica, String messaggio) {
        if(notifica instanceof Invito invito){
            List<UtenteVerificato> animatori = utenteRepository.findAllAnimatore();
            for(UtenteVerificato u :  animatori){
                if(u.getId().equals(invito.getEvento().getCreatore().getId())){
                    this.addNotifica(u, messaggio);
                }
            }
        }
    }
}
