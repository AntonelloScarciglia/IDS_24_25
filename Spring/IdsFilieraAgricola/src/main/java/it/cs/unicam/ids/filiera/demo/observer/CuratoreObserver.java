package it.cs.unicam.ids.filiera.demo.observer;

public class CuratoreObserver implements Observer{

    private UtenteRepository utenteRepository;

    @Override
    public void aggiorna(Notifica notifica, String messaggio) {
        if(notifica instanceof Prodotto prodotto){
            for(UtenteVerificato u : utenteRepository.findAllCuratori()){
                u.getNotifiche().add(messaggio);
            }
        }
    }
}

