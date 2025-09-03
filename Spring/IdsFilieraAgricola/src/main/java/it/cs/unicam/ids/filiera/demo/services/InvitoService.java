package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.InvitoDTO;
import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.InvitoMapper;
import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.RichiestaInvitoDTO;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.Venditore;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Evento;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import it.cs.unicam.ids.filiera.demo.entity.eventi.InvitoStato;
import it.cs.unicam.ids.filiera.demo.repositories.EventoRepository;
import it.cs.unicam.ids.filiera.demo.repositories.InvitoRepository;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitoService {

    private final InvitoRepository invitoRepo;
    private final EventoRepository eventoRepo;
    private final UtenteRepository utenteRepo;

    public List<InvitoDTO> creaInvito(UtenteVerificato creatore, Long eventoId, RichiestaInvitoDTO dto) {
        // 1) Carico l'evento dal DB o lancio eccezione se non esiste
        Evento evento = eventoRepo.findById(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));

        // 2) Autorizzazione: solo il creatore dell'evento può inviare inviti
        requireCreator(creatore, evento);

        // impossibile invitare se l'evento è terminato
        ensureEventoNonTerminato(evento);

        // 3) Validazione input
        if (dto.idUtentiList() == null || dto.idUtentiList().isEmpty()) {
            throw new IllegalArgumentException("La lista di utenti non può essere vuota");
        }

        // 4) Per ogni id utente nella richiesta, controllo che sia Venditore, evito duplicati e salvo invito
        List<InvitoDTO> risultati = new ArrayList<>();

        for (Long invId : dto.idUtentiList()) {
            // 1. Carico l'utente invitato dal DB o lancio eccezione se non esiste
            UtenteVerificato invitato = utenteRepo.findById(invId)
                    .orElseThrow(() -> new IllegalArgumentException("Utente con id " + invId + " non trovato"));

            // 2. Controllo che l'utente sia un Venditore
            if (!(invitato instanceof Venditore)) {
                throw new IllegalArgumentException("L'utente con id " + invId + " non è un Venditore");
            }

            // 3. Controllo che non esista già un invito per questo utente e evento
            var esistente = invitoRepo.findByEventoIdAndInvitatoId(eventoId, invId);
            if (esistente.isPresent()) {
                risultati.add(InvitoMapper.toDTO(esistente.get()));
                continue;
            }
            // 4. Creo e salvo il nuovo invito (in attesa by default)
            Invito inv = new Invito(evento, invitato, dto.messaggio());
            inv = invitoRepo.save(inv);
            risultati.add(InvitoMapper.toDTO(inv));
        }
        return risultati;
    }

    public InvitoDTO eliminaInvito(Long id) {
        throw new UnsupportedOperationException("Non implementato");
    }

    /**
     * Risponde a un invito (azione = accetta o rifiuta) - solo invitato
     */
    public InvitoDTO rispondiInvito(UtenteVerificato utente, Long invitoId, String azione) {

        var invito = invitoRepo.findById(invitoId)
                .orElseThrow(() -> new IllegalArgumentException("Invito non trovato"));

        // autorizzazione: solo l'invitato può rispondere
        if (!Objects.equals(invito.getInvitato().getId(), utente.getId())) {
            throw new IllegalStateException("Operazione consentita solo all'invitato");
        }

        // stato: posso rispondere solo se l'invito è in attesa
        if (invito.getStato() != InvitoStato.IN_ATTESA) {
            throw new IllegalStateException("Impossibile rispondere: invito già " + invito.getStato());
        }

        // impossibile rispondere se l'evento è terminato
        var evento = invito.getEvento();
        ensureEventoNonTerminato(evento);
        // azione: accetta o rifiuta
        azione = azione.toUpperCase();
        switch (azione) {
            case "ACCETTA" -> {
                evento.aggiungiPartecipante(utente); // <- aggiorna anche i posti disponibili
                invito.setStato(InvitoStato.ACCETTATO);
            }
            case "RIFIUTA" -> invito.setStato(InvitoStato.RIFIUTATO);
            default -> throw new IllegalArgumentException("Azione non valida: " + azione);
        }
        return InvitoMapper.toDTO(invitoRepo.save(invito));
    }


    /**
     * Recupera gli inviti ricevuti da un utente specifico.
     */
    public List<InvitoDTO> getInvitiRicevuti(UtenteVerificato invitato) {
        return invitoRepo.findByInvitatoId(invitato.getId())
                .stream()
                .map(InvitoMapper::toDTO)
                .toList();
    }


    /**
     * Lista tutti inviti di un evento (solo creatore)
     */
    public List<InvitoDTO> visualizzaTuttiInviti(UtenteVerificato creatore, Long eventoId) {
        Evento evento = eventoRepo.findById(eventoId)
                .orElseThrow(() -> new IllegalArgumentException("Evento non trovato"));

        requireCreator(creatore, evento);

        return invitoRepo.findByEventoId(eventoId)
                .stream()
                .map(InvitoMapper::toDTO)
                .toList();

    }


    private void requireCreator(UtenteVerificato creatore, Evento evento) {
        if (evento.getCreatore() == null || !evento.getCreatore().getId().equals(creatore.getId())) {
            throw new IllegalStateException("Operazione consentita solo al creatore dell'evento");
        }
    }

    private void ensureEventoNonTerminato(Evento e) {
        LocalDateTime now = LocalDateTime.now();
        if (e.getDataFine() != null && e.getDataFine().isBefore(now)) {
            String when = e.getDataFine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            throw new IllegalStateException("Evento terminato il " + when);
        }
    }

}
