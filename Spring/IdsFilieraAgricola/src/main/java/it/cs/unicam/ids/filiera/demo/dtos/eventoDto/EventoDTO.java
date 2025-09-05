package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

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

        // input: se null o <=0 => illimitato; se >0 => capienza fissa
        Integer capienzaMax,

        @JsonProperty(access = READ_ONLY)
        Integer postiRimanenti,

        @JsonProperty(access = READ_ONLY)
        Integer partecipantiCount,

        @JsonProperty(access = READ_ONLY)
        Long creatoreId,

        @JsonProperty(access = READ_ONLY)
        List<Long> partecipantiIds
) {
    @JsonIgnore
    @AssertTrue(message = "dataFine deve essere successiva a dataInizio")
    public boolean isValidDateRange() {
        return dataInizio == null || dataFine == null || dataFine.isAfter(dataInizio);
    }
}
