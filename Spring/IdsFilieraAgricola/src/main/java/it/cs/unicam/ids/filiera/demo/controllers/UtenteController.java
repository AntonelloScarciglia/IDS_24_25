package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.dtos.LoginDTO;
import it.cs.unicam.ids.filiera.demo.dtos.RegistrazioneDTO;
import it.cs.unicam.ids.filiera.demo.dtos.UtenteDTO;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

	private final UtenteService utenteService;

	@Autowired
	public UtenteController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	/**
	 * Registrazione di un utente, acquirente o venditore.
	 */
	@PostMapping("/registrazione")
	public ResponseEntity<String> registrazione(@RequestBody RegistrazioneDTO dto) {
		try {
			String result = utenteService.registrazioneUtente(dto);
			return ResponseEntity.ok(result);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * Login generico per utente (email, password).
	 */
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO dto) {
		try {
			String result = utenteService.login(dto.email(), dto.password());
			return ResponseEntity.ok(result);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/**
	 * Visualizza info utente.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> visualizzaUtente(@PathVariable int id) {
		return ResponseEntity.ok(utenteService.visualizzaUtente(id));
	}

	/**
	 * Restituisce prodotti posseduti da un venditore.
	 */
	@GetMapping("/{id}/prodotti")
	public ResponseEntity<List<Prodotto>> prodottiUtente(@PathVariable int id) {
		return ResponseEntity.ok(utenteService.prodottiPosseduti(id));
	}
}
