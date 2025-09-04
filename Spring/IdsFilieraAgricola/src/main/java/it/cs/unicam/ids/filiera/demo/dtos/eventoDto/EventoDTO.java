package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record EventoDTO(
        Long id,
        @NotBlank String titolo,
        @Size(max = 4000) String descrizione,
        @NotBlank String luogo,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        @FutureOrPresent
        LocalDateTime dataInizio,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        @Future
        LocalDateTime dataFine,
        boolean illimitato,          // input: true => posti illimitati
        @Positive(message = "capienzaMax deve essere > 0") Integer capienzaMax,// se illimitato=true può essere null in output
        Integer postiRimanenti,      // solo output
        Integer partecipantiCount,   // solo output
        Long creatoreId,             // output: id del creatore
        List<Long> partecipantiIds   // opzionale (solo output)
) {
    @AssertTrue(message = "dataFine deve essere successiva a dataInizio")
    public boolean isValidDateRange() {
        return dataInizio == null || dataFine == null || dataFine.isAfter(dataInizio);
    }

    @AssertTrue(message = "Se illimitato=false, capienzaMax è obbligatoria e > 0")
    public boolean isCapienzaCoerente() {
        return illimitato || (capienzaMax != null && capienzaMax > 0);
    }
}