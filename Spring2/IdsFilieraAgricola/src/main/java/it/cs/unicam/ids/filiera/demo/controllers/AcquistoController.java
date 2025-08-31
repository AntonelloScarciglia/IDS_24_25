package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.model.Carrello;
import it.cs.unicam.ids.filiera.demo.model.RigaCarrello;
import it.cs.unicam.ids.filiera.demo.services.AcquistoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/acquisto")
public class AcquistoController {

	@Autowired
	private AcquistoService serviceAcquisto;

	// Aggiungi al carrello
	@PostMapping("/carrello/{id}")
	public ResponseEntity<String> richiestaAggiungiAlCarrello(
			@PathVariable Long id,
			@RequestParam(defaultValue = "1") int qty,
			HttpSession session) {
		String risposta = serviceAcquisto.aggiungiAlCarrello(session, id, qty);
		return ResponseEntity.ok(risposta);
	}

	// Rimuovi dal carrello
	@DeleteMapping("/carrello/{id}")
	public ResponseEntity<String> richiestaRimuoviDalCarrello(
			@PathVariable Long id,
			HttpSession session) {
		String risposta = serviceAcquisto.rimuoviDalCarrello(session, id);
		return ResponseEntity.ok(risposta);
	}

	// Visualizza carrello (righe)
	@GetMapping("/carrello")
	public ResponseEntity<Collection<RigaCarrello>> richiestaVisualizzaCarrello(HttpSession session) {
		Carrello carrello = serviceAcquisto.getCarrello(session);
		return ResponseEntity.ok(carrello.getRighe());
	}

	// Svuota carrello
	@DeleteMapping("/carrello")
	public ResponseEntity<String> richiestaSvuotaCarrello(HttpSession session) {
		String risposta = serviceAcquisto.svuotaCarrello(session);
		return ResponseEntity.ok(risposta);
	}

	// Visualizza totale
	@GetMapping("/totale")
	public ResponseEntity<Float> richiestaVisualizzaTotale(HttpSession session) {
		float totale = serviceAcquisto.visualizzaTotale(session);
		return ResponseEntity.ok(totale);
	}

	// Acquista (conferma ordine)
	@PostMapping("/acquista")
	public ResponseEntity<String> richiestaAcquista(HttpSession session) {
		String risposta = serviceAcquisto.acquista(session);
		return ResponseEntity.ok(risposta);
	}
}
