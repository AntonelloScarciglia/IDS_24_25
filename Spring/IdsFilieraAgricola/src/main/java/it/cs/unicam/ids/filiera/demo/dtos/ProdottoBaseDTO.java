package it.cs.unicam.ids.filiera.demo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProdottoBaseDTO(
        Long vednitoreid,
        String nome,
        String categoria,
        BigDecimal prezzo,
        LocalDate dataScadenza
) {}