package it.cs.unicam.ids.filiera.demo.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;


public record ProdottoTrasformatoDTO(
        String nome,
        String categoria,
        BigDecimal prezzo,
        LocalDate dataScadenza,
        Long prodottoBaseId,
        String certificato,
        String metodoTrasformazione
) {}