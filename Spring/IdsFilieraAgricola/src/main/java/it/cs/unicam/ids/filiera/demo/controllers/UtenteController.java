package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.dtos.LoginDTO;
import it.cs.unicam.ids.filiera.demo.dtos.RegistrazioneDTO;
import it.cs.unicam.ids.filiera.demo.dtos.UtenteDTO;
import it.cs.unicam.ids.filiera.demo.dtos.WhoAmIDTO;
import it.cs.unicam.ids.filiera.demo.entity.Ordine;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.model.Sessione;
import it.cs.unicam.ids.filiera.demo.services.GestionaleService;
import it.cs.unicam.ids.filiera.demo.services.UtenteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    private final UtenteService utenteService;
	private Sessione sessione;


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
    public ResponseEntity<?> RichiestaVisualizzaUtente(@PathVariable int id) {
        return ResponseEntity.ok(utenteService.visualizzaUtente(id));
    }

    /**
     * Restituisce prodotti posseduti da un venditore.
     */
    @GetMapping("/{id}/prodotti")
    public ResponseEntity<List<Prodotto>> RichiestaProdottiUtente(@PathVariable int id) {
        return ResponseEntity.ok(utenteService.prodottiPosseduti(id));
    }


    /**
     * Restituisce gli ordini dell'utente in sessione.
     */
    @GetMapping("/ordini")
    public ResponseEntity<List<Ordine>> RichiestaGetOrdiniUtente(HttpSession session) {
        List<Ordine> ordini = utenteService.ordiniUtente(session);
        return ResponseEntity.ok(ordini);
    }

	@GetMapping("/notifiche/visualizza")
	public ResponseEntity<List<String>> RichiestaVisualizzaNotifiche(){
		List<String> notifiche = utenteService.visualizzaNotifiche(this.sessione.getUtente());
		return ResponseEntity.ok(notifiche);
	}

	@DeleteMapping("/notifiche/svuota")
	public ResponseEntity<String> richiestaSvuotaNotifiche(){
		return ResponseEntity.ok(utenteService.svuotaNotifiche(this.sessione.getUtente()));
	}

    @PostMapping("/login-test")
    public ResponseEntity<String> loginFittizio(HttpSession session) {
        utenteService.loginFittizio(session);
        return ResponseEntity.ok("Login fittizio completato");
    }


    @PostMapping("/login-animatore-test")
    public ResponseEntity<String> loginAnimatoreFittizio(HttpSession session) {
        utenteService.loginAnimatoreFittizio(session);
        return ResponseEntity.ok("Login animatore fittizio completato");
    }

    @PostMapping("/login-venditore-test")
    public ResponseEntity<String> loginVenditoreFittizio(HttpSession session) {
        utenteService.loginVenditoreFittizio(session);
        return ResponseEntity.ok("Login venditore fittizio completato");
    }


    // per testare chi è l'utente in sessione
    @GetMapping("/whoami")
    public ResponseEntity<?> whoAmI(HttpSession session) {

        Sessione s = (Sessione) session.getAttribute(GestionaleService.SESSIONE_KEY);
        UtenteVerificato u = (s != null) ? s.getUtente() : null;


        if (u == null) u = (UtenteVerificato) session.getAttribute("utente");

        if (u == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nessun utente in sessione");
        }

        WhoAmIDTO dto = new WhoAmIDTO(
                u.getId(),
                u.getNome(),
                u.getCognome(),
                u.getEmail(),
                (u.getRuolo() != null ? u.getRuolo().name() : u.getClass().getSimpleName()),
                session.getId()
        );
        return ResponseEntity.ok(dto);
    }

	// Lista di venditori
	@GetMapping("/venditori")
	public ResponseEntity<List<UtenteDTO>> richiestaVisualizzaVenditori() {
		return ResponseEntity.ok(utenteService.visualizzaVenditori());
	}

	// Utility: estrae l'utente dalla HttpSession
	private UtenteVerificato getUtenteCorrente(HttpSession httpSession) {
		Sessione s = (Sessione) httpSession.getAttribute(GestionaleService.SESSIONE_KEY);
		if (s == null || s.getUtente() == null) {
			throw new IllegalStateException("Utente non presente in sessione");
		}
		return s.getUtente();
	}
}
