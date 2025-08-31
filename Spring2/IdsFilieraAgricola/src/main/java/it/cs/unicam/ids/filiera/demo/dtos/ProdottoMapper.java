package it.cs.unicam.ids.filiera.demo.dtos;

import it.cs.unicam.ids.filiera.demo.entity.Bundle;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.ProdottoTrasformato;

import java.util.List;

public class ProdottoMapper {

    /** Entity → DTO (gestione polimorfica) */
    public static ProdottoDTO inDTO(Prodotto p) {
        if (p == null) return null;

        String tipo = "BASE";
        Long prodottoBaseId = null;
        String certificato = null;
        String metodo = null;
        List<Long> componenti = null;

        if (p instanceof ProdottoTrasformato pt) {
            tipo = "TRASFORMATO";
            prodottoBaseId = pt.getProdottoBaseId();
            certificato = pt.getCertificato();
            metodo = pt.getMetodoTrasformazione();
        } else if (p instanceof Bundle pb) {
            tipo = "BUNDLE";
            componenti = pb.getProdotti() == null
                    ? List.of()
                    : pb.getProdotti().stream()
                    .map(Prodotto::getId)
                    .toList();
        }

        return new ProdottoDTO(
                p.getId(),
                tipo,
                p.getVenditoreId(),
                p.getNome(),
                p.getCategoria(),
                p.getPrezzo(),
                p.getDataScadenza(),
                prodottoBaseId,
                certificato,
                metodo,
                componenti
        );
    }

    /** DTO → Entity (creazione grezza — i componenti per i bundle li aggiungi nel Service) */
    public static Prodotto inEntity(ProdottoDTO dto) {
        if (dto == null) return null;

        String tipo = dto.tipo() == null ? "BASE" : dto.tipo().toUpperCase();

        return switch (tipo) {
            case "TRASFORMATO" -> new ProdottoTrasformato(
                    dto.venditoreId(),
                    dto.nome(),
                    dto.categoria(),
                    dto.prezzo(),
                    dto.dataScadenza(),
                    dto.prodottoBaseId(),
                    dto.certificato(),
                    dto.metodoTrasformazione()
            );
            case "BUNDLE" -> new Bundle(
                    dto.venditoreId(),
                    dto.nome(),
                    dto.categoria(),
                    dto.prezzo(),
                    dto.dataScadenza()
            );
            default -> new Prodotto(
                    dto.venditoreId(),
                    dto.nome(),
                    dto.categoria(),
                    dto.prezzo(),
                    dto.dataScadenza()
            );
        };
    }
}
