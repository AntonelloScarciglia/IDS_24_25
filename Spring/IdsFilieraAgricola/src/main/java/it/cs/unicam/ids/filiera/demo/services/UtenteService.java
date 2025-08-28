package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.Venditore;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;

import java.util.List;

public class UtenteService {

	private UtenteRepository utenteRepository;
	private ProdottoService serviceProdotto;

	/**
	 * 
	 * @param email
	 * @param psw
	 */
	public String registrarsi(String email, String psw) {
		// TODO - implement UtenteService.registrarsi
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param venditore
	 */
	public String login(Venditore venditore) {
		// TODO - implement UtenteService.login
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public String visualizzaUtente(int id) {
		// TODO - implement UtenteService.visualizzaUtente
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public List<Prodotto> prodottiPosseduti(int id) {
		// TODO - implement UtenteService.prodottiPosseduti
		throw new UnsupportedOperationException();
	}

}