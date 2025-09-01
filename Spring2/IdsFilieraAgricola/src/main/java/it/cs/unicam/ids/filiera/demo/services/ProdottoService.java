package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.*;
import it.cs.unicam.ids.filiera.demo.entity.Bundle;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.entity.ProdottoTrasformato;
import it.cs.unicam.ids.filiera.demo.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
// Il servizio ProdottoService gestisce la logica di business relativa ai prodotti.
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    /**
     *crea un nuovo prodotto base
     * @param dtoProd
     */
    public String newProdotto(ProdottoDTO dtoProd) {
        if (dtoProd.venditoreId() == null) {
            throw new IllegalArgumentException("Il campo venditoreId è obbligatorio");
        }

        boolean campiTrasf = dtoProd.prodottoBaseId() != null
                || (dtoProd.certificato() != null && !dtoProd.certificato().isBlank())
                || (dtoProd.metodoTrasformazione() != null && !dtoProd.metodoTrasformazione().isBlank());

        boolean tipoNonBase = dtoProd.tipo() != null
                && !dtoProd.tipo().equalsIgnoreCase("BASE");

        if (campiTrasf || tipoNonBase) {
            throw new IllegalArgumentException(
                    "Per prodotti trasformati/bundle usa le rotte dedicate: " +
                            "POST /prodotti/trasformato o POST /prodotti/bundle."
            );
        }

        Prodotto prodotto = ProdottoMapper.inEntity(dtoProd); // qui sarà certamente BASE
        prodotto.setAttesa(true);
        prodottoRepository.save(prodotto);
        return "Prodotto creato con ID: " + prodotto.getId();
    }


    /**
     * aggiunge il prodotto in repo
     * @param prodotto
     */
    public Prodotto salvaProdotto(Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }

    /**
     * elimina il prodotto dalla repo
     * @param id
     */
    public Prodotto rimuoviProdotto(int id) {
        Long prodottoId = (long) id;
        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con ID: " + id));
        prodottoRepository.delete(prodotto);
        return prodotto;
    }


    /**
     * to string prodotto
     * @param id
     */
    public Prodotto visualizzaProdotto(int id) {
        return prodottoRepository.findById((long) id)
                .orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con ID: " + id));
    }

    /**
     * ritorna tutti i prodotti in stato approvato
     * @return
     */
    public List<Prodotto> visualizzaTuttiProdotti() {
        return prodottoRepository.findAll();
    }




    /**
     *cambia nome e prezzo prodotto
     * @param nome
     * @param prezzo
     */
    public Prodotto aggiornaProdotto(int id, String nome, BigDecimal prezzo) {
        Prodotto prodotto = visualizzaProdotto(id);
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        return prodottoRepository.save(prodotto);
    }


    /**
     * chiama repo per ottenere tutti i prodotti filtrati lutente con id
     * @param id
     */
    public List<Prodotto> visualizzaProdottiUtente(int id) {
        return prodottoRepository.findAll().stream()
                .filter(p -> p.getVenditoreId() != null && p.getVenditoreId().equals((long) id))
                .toList();
    }




    /**
     * Crea un prodotto trasformato a partire da un DTO, imposta lo stato in attesa e lo salva.
     */
    public Prodotto newProdottoTrasformato(ProdottoTrasformatoDTO dto) {
        if (dto.venditoreId() == null)
            throw new IllegalArgumentException("venditoreId obbligatorio");
        if (dto.prodottoBaseId() == null)
            throw new IllegalArgumentException("prodottoBaseId obbligatorio");


        Prodotto base = prodottoRepository.findById(dto.prodottoBaseId())
                .orElseThrow(() -> new IllegalArgumentException("Prodotto base non trovato: " + dto.prodottoBaseId()));

        if (base instanceof ProdottoTrasformato || base instanceof Bundle) {
            throw new IllegalArgumentException("Il prodotto base deve essere di tipo BASE.");
        }

        ProdottoTrasformato pt = new ProdottoTrasformato(
                dto.venditoreId(),
                dto.nome(),
                dto.categoria(),
                dto.prezzo(),
                dto.dataScadenza(),
                dto.prodottoBaseId(),
                dto.certificato(),
                dto.metodoTrasformazione()
        );
        pt.setAttesa(true); // nuovo => in attesa
        return prodottoRepository.save(pt);
    }


    /**
     *
     * @param bundleId
     * @param prodottoId
     * @return
     */

    @Transactional
    public Prodotto aggiungiProdottoBundle(Long bundleId, Long prodottoId) {
        Bundle bundle = prodottoRepository.findBundleWithProdotti(bundleId)
                .orElseThrow(() -> new IllegalArgumentException("Bundle non trovato"));
        if (!bundle.isAttesa()) throw new IllegalStateException("Bundle confermato: non modificabile.");

        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));

        bundle.getProdotti().add(prodotto);
        var totale = bundle.getProdotti().stream().map(Prodotto::getPrezzo)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        bundle.setPrezzo(totale);
        return prodottoRepository.save(bundle);
    }

    @Transactional
    public Prodotto rimuoviProdottoDaBundle(Long bundleId, Long prodottoId) {
        Bundle bundle = prodottoRepository.findBundleWithProdotti(bundleId)
                .orElseThrow(() -> new IllegalArgumentException("Bundle non trovato"));
        if (!bundle.isAttesa()) throw new IllegalStateException("Bundle confermato: non modificabile.");

        bundle.getProdotti().removeIf(p -> p.getId().equals(prodottoId));
        var totale = bundle.getProdotti().stream().map(Prodotto::getPrezzo)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        bundle.setPrezzo(totale);
        return prodottoRepository.save(bundle);
    }



    /**
     *filtro er ottenre i prodotti on id inserito
     * @param idVenditore
     */
    public List<Prodotto> getProdotti(int idVenditore) {
        return prodottoRepository.findAll().stream()
                .filter(p -> p.getVenditoreId() != null && p.getVenditoreId().equals((long) idVenditore))
                .toList();
    }


    /**
     * prende la lista del buundle il prezzo e il nome
     */
    public Prodotto newBundle(BundleDTO dto) {
        Bundle bundle = new Bundle(
                dto.venditoreId(),
                dto.nome(),
                dto.categoria(),
                BigDecimal.ZERO, // prezzo iniziale 0
                dto.dataScadenza()
        );

        bundle.setAttesa(true);
        bundle.setBundle(true);
        return prodottoRepository.save(bundle);
    }


    @Transactional
    public Prodotto confermaBundle(Long bundleId) {
        Bundle bundle = prodottoRepository.findBundleWithProdotti(bundleId)
                .orElseThrow(() -> new IllegalArgumentException("Bundle non trovato"));

        if (bundle.getProdotti() == null || bundle.getProdotti().isEmpty()) {
            throw new IllegalStateException("Impossibile confermare: il bundle è vuoto.");
        }

        // ricalcolo prezzo = somma componenti
        var totale = bundle.getProdotti().stream()
                .map(Prodotto::getPrezzo)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
        bundle.setPrezzo(totale);

        bundle.setAttesa(false); // confermato/approvato
        return prodottoRepository.save(bundle);
    }



    /**
     * impostata lo stato del prodotto in attesa true o false
     * @param attesa
     */
    public Prodotto setStato(int id, boolean attesa) {
        Prodotto prodotto = visualizzaProdotto(id);
        prodotto.setAttesa(attesa);
        return prodottoRepository.save(prodotto);
    }



    /**
     * ritorna i prodotti in attesa con stato non approvato
     * @return
     */
    public List<Prodotto> getProdottiInAttesa() {
        return prodottoRepository.findAll().stream()
                .filter(Prodotto::isAttesa)
                .toList();
    }

    public Prodotto approvaProdotto(int id) {
        Prodotto prodotto = visualizzaProdotto(id);
        prodotto.setAttesa(false); // approvato
        return prodottoRepository.save(prodotto);
    }

    public Prodotto rifiutaProdotto(int id) {
        Prodotto prodotto = visualizzaProdotto(id);
        prodottoRepository.delete(prodotto);
        return prodotto;
    }



    public List<ProdottoDTO> visualizzaTuttiProdottiDTO() {
        List<Prodotto> prodottiNormali = prodottoRepository.findAll().stream()
                .filter(p -> !(p instanceof Bundle))
                .toList();

        List<Bundle> bundles = prodottoRepository.findTuttiIBundleConProdotti();

        List<ProdottoDTO> result = new ArrayList<>();
        prodottiNormali.forEach(p -> result.add(ProdottoMapper.inDTO(p)));
        bundles.forEach(b -> result.add(ProdottoMapper.inDTO(b)));

        return result;
    }



    public List<ProdottoDTO> visualizzaProdottiApprovatiDTO() {
        List<Prodotto> prodottiNormali = prodottoRepository.findByAttesaFalse().stream()
                .filter(p -> !(p instanceof Bundle))
                .toList();

        List<Bundle> bundles = prodottoRepository.findBundleConfermatiConProdotti();

        List<ProdottoDTO> result = new ArrayList<>();
        prodottiNormali.forEach(p -> result.add(ProdottoMapper.inDTO(p)));
        bundles.forEach(b -> result.add(ProdottoMapper.inDTO(b)));

        return result;
    }




    /**
     *
     * @param prod
     */
    public String toStringProdotto(Prodotto prod) {
        String base = "Prodotto{id=%d, tipo=%s, nome='%s', categoria='%s', prezzo=%.2f, scadenza=%s, attesa=%s}"
                .formatted(
                        prod.getId(),
                        prod.getClass().getSimpleName(),
                        prod.getNome(),
                        prod.getCategoria(),
                        prod.getPrezzo(),
                        prod.getDataScadenza(),
                        prod.isAttesa()
                );

        if (prod instanceof ProdottoTrasformato pt) {
            return base + " [baseId=%d, certificato='%s', metodo='%s']"
                    .formatted(pt.getProdottoBaseId(), pt.getCertificato(), pt.getMetodoTrasformazione());
        }

        if (prod instanceof Bundle bundle) {
            return base + " [componenti=%s]"
                    .formatted(bundle.getProdotti().stream()
                            .map(p -> p.getId().toString())
                            .toList());
        }

        return base;
    }

}
