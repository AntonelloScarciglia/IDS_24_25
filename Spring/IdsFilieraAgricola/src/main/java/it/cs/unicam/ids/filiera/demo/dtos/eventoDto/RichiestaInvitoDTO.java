package it.cs.unicam.ids.filiera.demo.dtos.eventoDto;

import java.util.List;

public record RichiestaInvitoDTO (List<Long> idUtentiList, String messaggio) {
}
