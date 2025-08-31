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

    @Query("select b from Bundle b left join fetch b.prodotti where b.id = :id")
    Optional<Bundle> findBundleWithProdotti(@Param("id") Long id);
    List<Prodotto> findByAttesaTrue();
    List<Prodotto> findByAttesaFalse();
    List<Prodotto> findByCategoria(String categoria);
}
