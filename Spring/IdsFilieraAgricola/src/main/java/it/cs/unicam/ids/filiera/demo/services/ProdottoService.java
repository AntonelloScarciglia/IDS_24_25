package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.*;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        // TODO - implement ProdottoService.newProdotto
        throw new UnsupportedOperationException();
    }

    /**
     * aggiunge il prodotto in repo
     * @param prodotto
     */
    public Prodotto salvaProdotto(Prodotto prodotto) {
        // TODO - implement ProdottoService.salvaProdotto
        throw new UnsupportedOperationException();
    }

    /**
     * elimina il prodotto dalla repo
     * @param id
     */
    public Prodotto rimuoviProdotto(int id) {
        // TODO - implement ProdottoService.rimuoviProdotto
        throw new UnsupportedOperationException();
    }

    /**
     * to string prodotto
     * @param id
     */
    public Prodotto visualizzaProdotto(int id) {
        // TODO - implement ProdottoService.visualizzaProdotto
        throw new UnsupportedOperationException();
    }

    /**
     * ritorna tutti i prodotti in stato approvato
     * @return
     */
    public List<Prodotto> visualizzaTuttiProdotti() {
        // TODO - implement ProdottoService.visualizzaTuttiProdotti
        throw new UnsupportedOperationException();
    }

    /**
     * aggiungi certificato ni prod trasformati
     * @param c
     */
    public String aggiungiCertificato(String c) {
        // TODO - implement ProdottoService.aggiungiCertificato
        throw new UnsupportedOperationException();
    }

    /**
     *cambia nome e prezzo prodotto
     * @param nome
     * @param prezzo
     */
    public String aggiorna(String nome, BigDecimal prezzo) {
        // TODO - implement ProdottoService.modificaProdotto
        throw new UnsupportedOperationException();
    }


    /**
     * chiama repo per ottenere tutti i prodotti filtrati lutente con id
     * @param id
     */
    public List<Prodotto> visualizzaProdottiUtente(int id) {
        // TODO - implement ProdottoService.visualizzaProdottiUtente
        throw new UnsupportedOperationException();
    }


    /**
     * crea prodotto trasformato
     * @param dtoProdTrasf
     */
    public Prodotto newProdottoTrasformato(ProdottoDTO dtoProdTrasf) {
        // TODO - implement ProdottoService.newProdottoTrasformato
        throw new UnsupportedOperationException();
    }

    /**
     * aggiunge un prodotto alla lista bundle
     * @param prodotto
     */
    public String aggiungiProdottoBundle(Prodotto prodotto) {
        // TODO - implement ProdottoService.aggiungiProdottoBundle
        throw new UnsupportedOperationException();
    }

    /**
     *filtro er ottenre i prodotti on id inserito
     * @param id
     */
    public List<Prodotto> getProdotti(int id) {
        // TODO - implement ProdottoService.getProdotti
        throw new UnsupportedOperationException();
    }

    /**
     * prende la lista del buundle il prezzo e il nome
     */
    public Prodotto newBundle() {
        // TODO - implement ProdottoService.newBundle
        throw new UnsupportedOperationException();
    }

    /**
     * impostata lo stato del prodotto in attesa true o false
     * @param attesa
     */
    public Prodotto setStato(boolean attesa) {
        // TODO - implement ProdottoService.setStato
        throw new UnsupportedOperationException();
    }

    /**
     * ritorna i prodotti in attesa con stato non approvato
     * @return
     */
    public List<Prodotto> getProdottiInAttesa() {
        // TODO - implement ProdottoService.getProdottiInAttesa
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prod
     */
    public String ToStringProdotto(Prodotto prod) {
        // TODO - implement ProdottoService.ToStringProdotto
        throw new UnsupportedOperationException();
    }


}
