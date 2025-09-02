package it.cs.unicam.ids.filiera.demo.services;


import it.cs.unicam.ids.filiera.demo.model.Carrello;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquistoService {

	@Autowired
	private GestionaleService gestionaleService;

	public String aggiungiAlCarrello(HttpSession session, Long prodottoId, int qty) {
		gestionaleService.aggiungiAlCarrello(session, prodottoId, qty);
		return "Prodotto aggiunto al carrello";
	}

	public String rimuoviDalCarrello(HttpSession session, Long prodottoId) {
		gestionaleService.getCarrello(session).rimuovi(prodottoId);
		return "Prodotto rimosso dal carrello";
	}

	public String svuotaCarrello(HttpSession session) {
		gestionaleService.aggiornaCarrello(session);
		return "Carrello svuotato";
	}

	public String mostraContenutoCarrello(HttpSession session) {
		return gestionaleService.mostraContenutoCarrello(session);
	}

	public float visualizzaTotale(HttpSession session) {
		return gestionaleService.getCarrello(session).getTotale().floatValue();
	}

	public String acquista(HttpSession session) {
		try {
			gestionaleService.aggiungiOrdine(session);
			return "Ordine effettuato con successo";
		} catch (Exception e) {
			e.printStackTrace(); // Log in console
			return "Errore durante l'acquisto: " + e.getMessage();
		}
	}


	public Carrello getCarrello(HttpSession session) {
		return gestionaleService.getCarrello(session);
	}


}
