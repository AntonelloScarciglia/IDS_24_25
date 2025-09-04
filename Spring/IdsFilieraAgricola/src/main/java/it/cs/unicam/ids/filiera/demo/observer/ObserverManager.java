package it.cs.unicam.ids.filiera.demo.observer;

import it.cs.unicam.ids.filiera.demo.entity.Bundle;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import org.springframework.stereotype.Component;

@Component
public class ObserverManager {

    private final CuratoreObserver curatoreObserver;
    private final VenditoreObserver venditoreObserver;
    private final AcquirenteObserver acquirenteObserver;
    private final AnimatoreObserver animatoreObserver;

    public ObserverManager(CuratoreObserver curatoreObserver,
                           VenditoreObserver venditoreObserver,
                           AcquirenteObserver acquirenteObserver,
                           AnimatoreObserver animatoreObserver) {
        this.curatoreObserver = curatoreObserver;
        this.venditoreObserver = venditoreObserver;
        this.acquirenteObserver = acquirenteObserver;
        this.animatoreObserver = animatoreObserver;
    }

    public void notificaProdottoCaricato(Prodotto prodotto) {
        curatoreObserver.aggiorna(prodotto, null);
    }

    public void notificaProdottoAccettato(Prodotto prodotto) {
        String msg = "Il prodotto " + prodotto.getNome() + " è stato accettato";
        venditoreObserver.aggiorna(prodotto, msg);
        acquirenteObserver.aggiorna(prodotto, null);
    }

    public void notificaProdottoRifiutato(Prodotto prodotto) {
        String msg = "Il prodotto " + prodotto.getNome() + " è stato rifiutato" ;
        venditoreObserver.aggiorna(prodotto, msg);
    }

    public void notificaProdottoAggiornato(Prodotto prodotto) {
        String msg = "Il prodotto : " + prodotto.getNome() + " è stato modificato";
        acquirenteObserver.aggiorna(prodotto,msg);
    }

    public void notificaProdottoAggiuntoBundle(Bundle bundle, Prodotto prodotto) {
        String msg = "Il prodotto : " + prodotto.getNome() + " è stato aggiunto al bundle : " + bundle.getNome();
        acquirenteObserver.aggiorna(bundle,msg);
        venditoreObserver.aggiorna(bundle,msg);
    }

    public void notificaProdottoRimossoBundle(Bundle bundle, Prodotto prodotto) {
        String msg = "Il prodotto : " + prodotto.getNome() + " è stato rimosso dal bundle : " + bundle.getNome();
        acquirenteObserver.aggiorna(bundle,msg);
        venditoreObserver.aggiorna(bundle,msg);
    }

    public void notificaInvitoCreato(Invito invito){
        String msg = "Ti hanno mandato un invito : " + invito.toString();
        venditoreObserver.aggiorna(invito, msg);
        animatoreObserver.aggiorna(invito, msg);
    }

    public void notificaInvitoEliminato(Invito invito){
        String msg = "Invito : " + invito.toString() + " eliminato";
        venditoreObserver.aggiorna(invito, msg);
        animatoreObserver.aggiorna(invito, msg);
    }

    public void notificaInvitoAccettato(Invito invito){
        String msg = "Invito : " + invito.toString() + " accettato";
        animatoreObserver.aggiorna(invito, msg);
    }

    public void notificaInvitoRifiutato(Invito invito){
        String msg = "Invito : " + invito.toString() + " rifiutato";
        animatoreObserver.aggiorna(invito, msg);
    }

}
