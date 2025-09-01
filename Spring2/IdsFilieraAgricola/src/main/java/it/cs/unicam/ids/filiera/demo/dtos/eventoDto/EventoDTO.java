package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EventoDTO(
        Long id,
        String titolo,
        String descrizione,
        String luogo,
        LocalDateTime dataInizio,
        LocalDateTime dataFine,
        boolean illimitato,          // input: true => posti illimitati
        Integer postiDisponibili,    // se illimitato=true, può essere null in output
        Integer partecipantiCount,   // solo output
        Long creatoreId,             // output: id del creatore
        List<Long> partecipantiIds   // opzionale (solo output)
) {
}
