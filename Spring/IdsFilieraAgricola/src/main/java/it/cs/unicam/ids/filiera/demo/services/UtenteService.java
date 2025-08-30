package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.RegistrazioneDTO;
import it.cs.unicam.ids.filiera.demo.entity.*;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UtenteService {

	private final UtenteRepository utenteRepository;
	private final ProdottoService prodottoService;

	@Autowired
	public UtenteService(UtenteRepository utenteRepository, ProdottoService prodottoService) {
		this.utenteRepository = utenteRepository;
		this.prodottoService = prodottoService;
	}

	public String registrati(String nome, String cognome, String email, String password) {
		Acquirente utente = new Acquirente(nome, cognome, email, password);
		utenteRepository.save(utente);
		return "Acquirente registrato con ID: " + utente.getId();
	}

	public String registrazioneUtente(RegistrazioneDTO dto) {
		if ("VENDITORE".equalsIgnoreCase(dto.tipo())) {
			if (dto.codiceFiscale() == null) {
				throw new IllegalArgumentException("Codice fiscale richiesto per i venditori");
			}
			Venditore v = new Produttore(dto.nome(), dto.cognome(), dto.email(), dto.password(), dto.codiceFiscale());
			utenteRepository.save(v);
			return "Venditore registrato con ID: " + v.getId();
		} else if ("ACQUIRENTE".equalsIgnoreCase(dto.tipo())) {
			UtenteVerificato u = new Acquirente(dto.nome(), dto.cognome(), dto.email(), dto.password());
			utenteRepository.save(u);
			return "Acquirente registrato con ID: " + u.getId();
		}
		throw new IllegalArgumentException("Tipo utente non valido");
	}

	public String login(String email, String password) {
		UtenteVerificato utente = utenteRepository.findByEmail(email);
		if (utente == null || !utente.getPassword().equals(password)) {
			throw new IllegalArgumentException("Credenziali non valide");
		}
		String ruolo = utente.getClass().getSimpleName();
		return "Login effettuato per " + ruolo + " con ID: " + utente.getId();
	}

	public UtenteVerificato visualizzaUtente(int id) {
		return utenteRepository.findById((long) id)
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato con ID: " + id));
	}

	public List<Prodotto> prodottiPosseduti(int idVenditore) {
		return prodottoService.getProdotti(idVenditore);
	}
}



