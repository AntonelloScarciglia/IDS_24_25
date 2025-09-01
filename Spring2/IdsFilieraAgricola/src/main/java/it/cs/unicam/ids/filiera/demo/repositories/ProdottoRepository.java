package it.cs.unicam.ids.filiera.demo.repositories;

import it.cs.unicam.ids.filiera.demo.entity.Bundle;
import it.cs.unicam.ids.filiera.demo.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findByVenditoreId(Long venditoreId);

    List<Prodotto> findByAttesaTrue();

    List<Prodotto> findByAttesaFalse();

    List<Prodotto> findByCategoria(String categoria);

    // ✅ Per ottenere un singolo bundle con i prodotti
    @Query("SELECT b FROM Bundle b LEFT JOIN FETCH b.prodotti WHERE b.id = :id")
    Optional<Bundle> findBundleWithProdotti(@Param("id") Long id);

    // ✅ Per ottenere tutti i bundle approvati con i prodotti
    @Query("SELECT DISTINCT b FROM Bundle b LEFT JOIN FETCH b.prodotti WHERE b.attesa = false")
    List<Bundle> findBundleConfermatiConProdotti();

    // ✅ Per ottenere tutti i bundle (anche in attesa) con i prodotti
    @Query("SELECT DISTINCT b FROM Bundle b LEFT JOIN FETCH b.prodotti")
    List<Bundle> findTuttiIBundleConProdotti();
}