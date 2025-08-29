package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.entity.Ordine;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.model.Carrello;
import it.cs.unicam.ids.filiera.demo.model.Sessione;
import it.cs.unicam.ids.filiera.demo.repositories.OrdineRepository;
import it.cs.unicam.ids.filiera.demo.repositories.ProdottoRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestionaleService {

	public static final String SESSIONE_KEY = "SESSIONE";

	private final OrdineRepository ordineRepository;
	private final ProdottoRepository prodottoRepository;

	public GestionaleService(OrdineRepository ordineRepository,
							 ProdottoRepository prodottoRepository) {
		this.ordineRepository = ordineRepository;
		this.prodottoRepository = prodottoRepository;
	}

	private Sessione getOrCreate(HttpSession httpSession) {
		Sessione s = (Sessione) httpSession.getAttribute(SESSIONE_KEY);
		if (s == null) {
			s = new Sessione(); // utente potrai impostarlo altrove (login)
			httpSession.setAttribute(SESSIONE_KEY, s);
		}
		return s;
	}


	/** Inizializza/ritorna la Sessione applicativa */
	public Sessione newSessione(HttpSession httpSession) {
		return getOrCreate(httpSession);
	}

	/** Ritorna il contenuto del carrello in formato stringa */
	@Transactional(readOnly = true)
	public String mostraContenutoCarrello(HttpSession httpSession) {
		return getOrCreate(httpSession).mostraContenuto();
	}

	/** Aggiunge un prodotto al carrello cercandolo per id (usato dal Controller) */
	public Carrello aggiungiAlCarrello(HttpSession session, Long prodottoId, int qty) {
		Prodotto p = prodottoRepository.findById(prodottoId)
				.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato: " + prodottoId));
		return aggiungiAlCarrello(session, p, qty);
	}

	/** Aggiunge un prodotto già caricato al carrello (riusata internamente) */
	public Carrello aggiungiAlCarrello(HttpSession session, Prodotto p, int qty) {
		if (p == null) throw new IllegalArgumentException("Prodotto nullo");
		Sessione s = getOrCreate(session);
		int q = Math.max(1, qty);
		s.getCarrello().aggiungi(p, (long) q);
		session.setAttribute(SESSIONE_KEY, s);
		return s.getCarrello();
	}

	/** Svuota il carrello e restituisce lo stato aggiornato */
	public Carrello aggiornaCarrello(HttpSession httpSession) {
		Sessione s = getOrCreate(httpSession);
		s.aggiornaCarrelloSvuota();
		httpSession.setAttribute(SESSIONE_KEY, s);
		return s.getCarrello();
	}

	/** Crea e persiste un ordine dal carrello; poi svuota il carrello */
	public Ordine aggiungiOrdine(HttpSession httpSession) {
		Sessione s = (Sessione) httpSession.getAttribute(SESSIONE_KEY);
		if (s == null) throw new IllegalStateException("Sessione non inizializzata");

		Carrello carrello = s.getCarrello();
		if (carrello == null || carrello.isVuoto())
			throw new IllegalStateException("Carrello vuoto");

		UtenteVerificato utente = s.getUtente();
		if (utente == null)
			throw new IllegalStateException("Utente non presente in sessione");

		Ordine ordine = new Ordine(utente, carrello.getTotale());
		ordine = ordineRepository.save(ordine);

		s.aggiornaCarrelloSvuota();
		httpSession.setAttribute(SESSIONE_KEY, s);

		return ordine;
	}

	/** Alias void per compatibilità col tuo design */
	public void newOrdine(HttpSession httpSession) {
		aggiungiOrdine(httpSession);
	}
}
