package it.cs.unicam.ids.filiera.demo.dtos;


import it.cs.unicam.ids.filiera.demo.entity.Prodotto;

public class ProdottoMapper {
    // Il mapper ProdottoMapper è responsabile della conversione tra entità Prodotto e DTO ProdottoDTO.
    // Implementa i metodi per convertire un Prodotto in ProdottoDTO e viceversa.

    public static ProdottoDTO inDTO(Prodotto prodotto) {
       throw new UnsupportedOperationException();
    }

    public static Prodotto inEntity(ProdottoDTO prodottoDTO) {
        throw new UnsupportedOperationException();
    }
}
