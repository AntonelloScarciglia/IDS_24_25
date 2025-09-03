package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.EventoDTO;
import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.EventoMapper;
import it.cs.unicam.ids.filiera.demo.entity.Animatore;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Evento;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.repositories.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }


    /* =========================================
        METODI DI LETTURA
        =========================================
     */
    @Transactional(readOnly = true)
    public List<EventoDTO> visualizzaTuttiEventi() {
        return eventoRepository.findAll()
                .stream()
                .map(EventoMapper::toDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public EventoDTO visualizzaEvento(Long id) {
        Evento e = eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento con id " + id + " non trovato"));
        return EventoMapper.toDto(e);
    }

    /**
     * Visualizza gli eventi creati dall'animatore.
     * Se l'animatore non ha creato eventi, viene lanciata un'eccezione.
     */
    @Transactional(readOnly = true)
    public List<EventoDTO> visualizzaMieiEventi(UtenteVerificato animatore) {
        controllaAnimatore(animatore);
        List<Evento> eventi = eventoRepository.findByCreatoreId(animatore.getId());
        if (eventi.isEmpty()) {
            throw new IllegalStateException("L'animatore non ha creato eventi");
        }
        return eventi.stream()
                .map(EventoMapper::toDto)
                .toList();
    }

    /* =========================================
        METODI DI SCRITTURA
       =========================================
     */
    public EventoDTO creaEvento(UtenteVerificato animatore, EventoDTO evento) {
        controllaAnimatore(animatore);
        Evento e = EventoMapper.toEntity(evento);
        e.setCreatore(animatore);
        e = eventoRepository.save(e);
        return EventoMapper.toDto(e);
    }

    public EventoDTO modificaEvento(UtenteVerificato animatore, Long id, EventoDTO evento) {
        controllaAnimatore(animatore);

        Evento e = eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento con id " + id + " non trovato"));

        controllaCreatore(animatore, e);

        EventoMapper.applicaModifica(e, evento);
        e = eventoRepository.save(e);
        return EventoMapper.toDto(e);
    }

    public boolean eliminaEvento(UtenteVerificato animatore, Long id) {
        controllaAnimatore(animatore);

        Evento e = eventoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento con id " + id + " non trovato"));

        controllaCreatore(animatore, e);

        eventoRepository.delete(e);
        return true;
    }


    // Utility
    private void controllaAnimatore(UtenteVerificato user) {
        if (!(user instanceof Animatore)) {
            throw new IllegalStateException("Operazione consentita solo ad ANIMATORE");
        }
    }

    private void controllaCreatore(UtenteVerificato user, Evento e) {
        if (e.getCreatore() == null || !e.getCreatore().getId().equals(user.getId())) {
            throw new IllegalStateException("Operazione consentita solo al creatore dell'evento");
        }
    }
}
