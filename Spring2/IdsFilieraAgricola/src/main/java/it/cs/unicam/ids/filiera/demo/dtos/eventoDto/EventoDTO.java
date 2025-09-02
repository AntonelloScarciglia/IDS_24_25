package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import java.time.LocalDateTime;
import java.util.List;

public record EventoDTO(
        Long id,
        String titolo,
        String descrizione,
        String luogo,
        LocalDateTime dataInizio,
        LocalDateTime dataFine,
        boolean illimitato,          // input: true => posti illimitati
        Integer capienzaMax,         // se illimitato=true può essere null in output
        Integer postiRimanenti,      // solo output
        Integer partecipantiCount,   // solo output
        Long creatoreId,             // output: id del creatore
        List<Long> partecipantiIds   // opzionale (solo output)
) {}