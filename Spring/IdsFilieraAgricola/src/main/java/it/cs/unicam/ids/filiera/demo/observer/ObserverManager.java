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

    public ObserverManager(CuratoreObserver curatoreObserver,
                           VenditoreObserver venditoreObserver,
                           AcquirenteObserver acquirenteObserver) {
        this.curatoreObserver = curatoreObserver;
        this.venditoreObserver = venditoreObserver;
        this.acquirenteObserver = acquirenteObserver;
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
        venditoreObserver.aggiornaInv(invito, msg);
        //animatoreObserver
    }

    public void notificaInvitoEliminato(Invito invito){
        String msg = "Invito : " + invito.toString() + " eliminato";
        venditoreObserver.aggiornaInv(invito, msg);
        //animatoreObserver
    }

    //notificaInvitoAccettato

    //notificaInvitoRifiutato
}
