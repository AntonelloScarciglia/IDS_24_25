package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.Venditore;
import it.cs.unicam.ids.filiera.demo.services.UtenteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/utenti")
public class UtenteController {

	private UtenteService serviceUtente;

	/**
	 * 
	 * @param email
	 * @param psw
	 */
	public String registrarsi(String email, String psw) {
		// TODO - implement UtenteController.registrarsi
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param utente
	 */
	public String login(Venditore utente) {
		// TODO - implement UtenteController.login
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public String richiestaVisualizzaUtente(int id) {
		// TODO - implement UtenteController.richiestaVisualizzaUtente
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public List<Prodotto> richiestaProdottiPosseduti(int id) {
		// TODO - implement UtenteController.richiestaProdottiPosseduti
		throw new UnsupportedOperationException();
	}

}