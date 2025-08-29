package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.entity.*;
import it.cs.unicam.ids.filiera.demo.services.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/marketplace")
public class GestionaleController {



	/**
	 * 
	 * @param prodotto
	 */
	public Carrello aggiungiAlCarrello(int prodotto) {
		// TODO - implement GestionaleController.aggiungiAlCarrello
		throw new UnsupportedOperationException();
	}

	public String richiestaContenutoCarrello() {
		// TODO - implement GestionaleController.richiestaContenutoCarrello
		throw new UnsupportedOperationException();
	}

	public Ordine richiestaAggiungiOrdine() {
		// TODO - implement GestionaleController.richiestaAggiungiOrdine
		throw new UnsupportedOperationException();
	}

}