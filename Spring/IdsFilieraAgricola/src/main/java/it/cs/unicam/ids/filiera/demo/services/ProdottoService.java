package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.ProdottoDTO;
import it.cs.unicam.ids.filiera.demo.model.Prodotto;
import it.cs.unicam.ids.filiera.demo.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Il servizio ProdottoService gestisce la logica di business relativa ai prodotti.
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    /**
     *
     * @param dtoProd
     */
    public String newProdotto(ProdottoDTO dtoProd) {
        // TODO - implement ProdottoService.newProdotto
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prodotto
     */
    public Prodotto salvaProdotto(Prodotto prodotto) {
        // TODO - implement ProdottoService.salvaProdotto
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public Prodotto rimuoviProdotto(int id) {
        // TODO - implement ProdottoService.rimuoviProdotto
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public Prodotto visualizzaProdotto(int id) {
        // TODO - implement ProdottoService.visualizzaProdotto
        throw new UnsupportedOperationException();
    }

    public List<Prodotto> visualizzaTuttiProdotti() {
        // TODO - implement ProdottoService.visualizzaTuttiProdotti
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param c
     */
    public String aggiungiCertificato(String c) {
        // TODO - implement ProdottoService.aggiungiCertificato
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @param nome
     * @param quantita
     */
    public String modificaProdotto(int id, String nome, int quantita) {
        // TODO - implement ProdottoService.modificaProdotto
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param dtoBundle
     */
    public String creaBundle(ProdottoDTO dtoBundle) {
        // TODO - implement ProdottoService.creaBundle
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public List<Prodotto> visualizzaProdottiUtente(int id) {
        // TODO - implement ProdottoService.visualizzaProdottiUtente
        throw new UnsupportedOperationException();
    }


    /**
     *
     * @param dtoProdTrasf
     */
    public Prodotto newProdottoTrasformato(ProdottoDTO dtoProdTrasf) {
        // TODO - implement ProdottoService.newProdottoTrasformato
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param prodotto
     */
    public String aggiungiProdottoBundle(Prodotto prodotto) {
        // TODO - implement ProdottoService.aggiungiProdottoBundle
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     */
    public List<Prodotto> getProdotti(int id) {
        // TODO - implement ProdottoService.getProdotti
        throw new UnsupportedOperationException();
    }

    public Prodotto newBundle() {
        // TODO - implement ProdottoService.newBundle
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param attesa
     */
    public Prodotto setStato(boolean attesa) {
        // TODO - implement ProdottoService.setStato
        throw new UnsupportedOperationException();
    }

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

    /**
     *
     * @param prodotto
     */
    public Prodotto aggiorna(int prodotto) {
        // TODO - implement ProdottoService.aggiorna
        throw new UnsupportedOperationException();
    }

}
