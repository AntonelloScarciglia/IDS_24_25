package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Evento;

import java.util.List;

public class EventoMapper {

    public static EventoDTO toDto(Evento e) {
        if (e == null) return null;

        boolean illimitato = e.getCapienzaMax() <= 0;
        Integer capienzaMax = illimitato ? null : e.getCapienzaMax();

        List<UtenteVerificato> partecipanti = e.getPartecipanti();
        int partecipantiCount = (partecipanti == null) ? 0 : partecipanti.size();
        List<Long> partecipantiIds = (partecipanti == null)
                ? List.of()
                : partecipanti.stream().map(UtenteVerificato::getId).toList();

        Long creatoreId = (e.getCreatore() != null) ? e.getCreatore().getId() : null;

        // Calcolo dei posti rimanenti (se illimitato → null)
        Integer postiRimanenti = illimitato
                ? null
                : Math.max(0, e.getCapienzaMax() - partecipantiCount);


        return new EventoDTO(
                e.getId(),
                e.getTitolo(),
                e.getDescrizione(),
                e.getLuogo(),
                e.getDataInizio(),
                e.getDataFine(),
                illimitato,
                capienzaMax,
                postiRimanenti,
                partecipantiCount,
                creatoreId,
                partecipantiIds
        );
    }

    /** entity per create (creatore NON viene impostato qui: lo mette il service) */
    public static Evento toEntity(EventoDTO dto) {
        if (dto == null) return null;
        Evento e = new Evento();
        applicaModifica(e, dto);
        return e;
    }

    /** update dei campi modificabili (creatore NON viene toccato) */
    public static void applicaModifica(Evento e, EventoDTO dto) {
        if (e == null || dto == null) return;

        e.setTitolo(dto.titolo());
        e.setDescrizione(dto.descrizione());
        e.setLuogo(dto.luogo());
        e.setDataInizio(dto.dataInizio());
        e.setDataFine(dto.dataFine());

        if (dto.illimitato()) {
            //0 => illimitato
            e.setPostiDisponibili(0);
        } else {
            Integer capienza = dto.capienzaMax();
            e.setPostiDisponibili(capienza != null ? capienza : 0);
        }
    }
}

