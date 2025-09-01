package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import it.cs.unicam.ids.filiera.demo.dtos.eventoDto.EventoDTO;
import it.cs.unicam.ids.filiera.demo.entity.Evento;
import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;

import java.util.List;

public class EventoMapper {
    public static EventoDTO toDto(Evento e) {
        if (e == null) return null;

        boolean illimitato = e.getPostiDisponibili() <= 0;
        Integer posti = illimitato ? null : e.getPostiDisponibili();

        List<UtenteVerificato> partecipanti = e.getPartecipanti();
        int count = partecipanti == null ? 0 : partecipanti.size();
        List<Long> ids = partecipanti == null ? List.of()
                : partecipanti.stream().map(UtenteVerificato::getId).toList();

        Long creatoreId = (e.getCreatore() != null) ? e.getCreatore().getId() : null;

        return new EventoDTO(
                e.getId(),
                e.getTitolo(),
                e.getDescrizione(),
                e.getLuogo(),
                e.getDataInizio(),
                e.getDataFine(),
                illimitato,
                posti,
                count,
                creatoreId,
                ids
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
            e.setPostiDisponibili(0);
        } else {
            Integer posti = dto.postiDisponibili();
            e.setPostiDisponibili(posti != null ? posti : 0);
        }
    }
}
