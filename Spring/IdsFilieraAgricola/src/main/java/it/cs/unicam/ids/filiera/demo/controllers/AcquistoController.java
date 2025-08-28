package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.services.AcquistoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/acquisto")
public class AcquistoController {

	private AcquistoService serviceAcquisto;

	/**
	 * 
	 * @param p
	 */
	public String richiestaAggiungiAlCarrello(Prodotto p) {
		// TODO - implement AcquistoController.richiestaAggiungiAlCarrello
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public String richiestaRimuoviDalCarrello(int id) {
		// TODO - implement AcquistoController.richiestaRimuoviDalCarrello
		throw new UnsupportedOperationException();
	}

	public List<Prodotto> richiestaVisualizzaCarrello() {
		// TODO - implement AcquistoController.richiestaVisualizzaCarrello
		throw new UnsupportedOperationException();
	}

	public String richiestaSvuotaCarrello() {
		// TODO - implement AcquistoController.richiestaSvuotaCarrello
		throw new UnsupportedOperationException();
	}

	public float richiestaVisualizzaTotale() {
		// TODO - implement AcquistoController.richiestaVisualizzaTotale
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param s
	 */
	public String richiestaAcquista(int s) {
		// TODO - implement AcquistoController.richiestaAcquista
		throw new UnsupportedOperationException();
	}

}