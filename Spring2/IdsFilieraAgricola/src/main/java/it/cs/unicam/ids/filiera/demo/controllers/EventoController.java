package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.EventoDTO;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.model.Sessione;
import it.cs.unicam.ids.filiera.demo.services.EventoService;
import it.cs.unicam.ids.filiera.demo.services.GestionaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping()
    public ResponseEntity<List<EventoDTO>> richiestaVisualizzaTuttiEventi() {
        return ResponseEntity.ok(eventoService.visualizzaTuttiEventi());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> richiestaVisualizzaEvento(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.visualizzaEvento(id));
    }

    @PostMapping("/crea")
    public ResponseEntity<EventoDTO> richiestaCreaEvento(@RequestBody EventoDTO eventoDTO, HttpSession session) {
        UtenteVerificato u = getUtenteCorrente(session);
        return ResponseEntity.ok(eventoService.creaEvento(u, eventoDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EventoDTO> richiestaModificaEvento(@PathVariable Long id,
                                                             @RequestBody EventoDTO eventoDTO,
                                                             HttpSession session) {
        UtenteVerificato u = getUtenteCorrente(session);
        return ResponseEntity.ok(eventoService.modificaEvento(u, id, eventoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> richiestaEliminaEvento(@PathVariable Long id, HttpSession session) {
        UtenteVerificato u = getUtenteCorrente(session);
        return ResponseEntity.ok(eventoService.eliminaEvento(u, id));
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Utility: estrae l'utente applicativo dalla HttpSession
    private UtenteVerificato getUtenteCorrente(HttpSession httpSession) {
        Sessione s = (Sessione) httpSession.getAttribute(GestionaleService.SESSIONE_KEY);
        if (s == null || s.getUtente() == null) {
            throw new IllegalStateException("Utente non presente in sessione");
        }
        return s.getUtente();
    }




}



