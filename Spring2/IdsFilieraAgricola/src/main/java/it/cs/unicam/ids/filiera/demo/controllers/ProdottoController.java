package it.cs.unicam.ids.filiera.demo.controllers;

import it.cs.unicam.ids.filiera.demo.dtos.BundleDTO;
import it.cs.unicam.ids.filiera.demo.dtos.ProdottoDTO;
import it.cs.unicam.ids.filiera.demo.dtos.ProdottoTrasformatoDTO;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import it.cs.unicam.ids.filiera.demo.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    // POST /prodotti - crea un nuovo prodotto base
    @PostMapping
    public ResponseEntity<String> creaProdotto(@RequestBody ProdottoDTO dto) {
        String result = prodottoService.newProdotto(dto);
        return ResponseEntity.ok(result);
    }

    // GET /prodotti - tutti i prodotti
    @GetMapping
    public ResponseEntity<List<Prodotto>> getTuttiProdotti() {
        return ResponseEntity.ok(prodottoService.visualizzaTuttiProdotti());
    }

    // GET /prodotti/{id} - prodotto singolo
    @GetMapping("/{id}")
    public ResponseEntity<Prodotto> getProdotto(@PathVariable int id) {
        return ResponseEntity.ok(prodottoService.visualizzaProdotto(id));
    }

    // DELETE /prodotti/{id} - elimina prodotto
    @DeleteMapping("/{id}")
    public ResponseEntity<Prodotto> deleteProdotto(@PathVariable int id) {
        return ResponseEntity.ok(prodottoService.rimuoviProdotto(id));
    }

    // GET /prodotti/in-attesa - prodotti ancora non approvati
    @GetMapping("/in-attesa")
    public ResponseEntity<List<Prodotto>> getProdottiInAttesa() {
        return ResponseEntity.ok(prodottoService.getProdottiInAttesa());
    }

    // PUT /prodotti/{id}/approva - approva prodotto
    @PutMapping("/{id}/approva")
    public ResponseEntity<Prodotto> approvaProdotto(@PathVariable int id) {
        return ResponseEntity.ok(prodottoService.approvaProdotto(id));
    }

    // DELETE /prodotti/{id}/rifiuta - rifiuta prodotto
    @DeleteMapping("/{id}/rifiuta")
    public ResponseEntity<Prodotto> rifiutaProdotto(@PathVariable int id) {
        return ResponseEntity.ok(prodottoService.rifiutaProdotto(id));
    }

    // PUT /prodotti/{id}?nome=...&prezzo=... - aggiorna nome e prezzo
    @PutMapping("/{id}")
    public ResponseEntity<Prodotto> aggiornaNomePrezzo(
            @PathVariable int id,
            @RequestParam String nome,
            @RequestParam BigDecimal prezzo) {
        Prodotto aggiornato = prodottoService.aggiornaProdotto(id, nome, prezzo);
        return ResponseEntity.ok(aggiornato);
    }

    // GET /prodotti/venditore/{id} - prodotti per venditore
    @GetMapping("/venditore/{id}")
    public ResponseEntity<List<Prodotto>> getProdottiPerVenditore(@PathVariable int id) {
        return ResponseEntity.ok(prodottoService.getProdotti(id));
    }

    // POST /prodotti/trasformato - crea prodotto trasformato
    @PostMapping("/trasformato")
    public ResponseEntity<Prodotto> creaProdottoTrasformato(@RequestBody ProdottoTrasformatoDTO dto) {
        try {
            Prodotto nuovoProdotto = prodottoService.newProdottoTrasformato(dto);
            return ResponseEntity.ok(nuovoProdotto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // POST /prodotti/bundle - crea nuovo bundle
    @PostMapping("/bundle")
    public ResponseEntity<Prodotto> creaBundle(@RequestBody BundleDTO dto) {
        Prodotto bundle = prodottoService.newBundle(dto);
        return ResponseEntity.ok(bundle);
    }

    // PATCH /prodotti/bundle/{bundleId}/aggiungi/{prodottoId} - aggiunge prodotto al bundle
    @PatchMapping("/bundle/{bundleId}/aggiungi/{prodottoId}")
    public ResponseEntity<Prodotto> aggiungiProdottoABundle(
            @PathVariable Long bundleId,
            @PathVariable Long prodottoId) {
        Prodotto result = prodottoService.aggiungiProdottoBundle(bundleId, prodottoId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/bundle/{bundleId}/rimuovi/{prodottoId}")
    public ResponseEntity<Prodotto> rimuoviProdottoDaBundle(
            @PathVariable Long bundleId,
            @PathVariable Long prodottoId) {
        Prodotto result = prodottoService.rimuoviProdottoDaBundle(bundleId, prodottoId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/bundle/{id}/conferma")
    public ResponseEntity<Prodotto> confermaBundle(@PathVariable Long id) {
        Prodotto bundle = prodottoService.confermaBundle(id);
        return ResponseEntity.ok(bundle);
    }


}
