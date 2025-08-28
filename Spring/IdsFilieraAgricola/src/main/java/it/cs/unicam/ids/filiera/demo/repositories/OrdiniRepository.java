package it.cs.unicam.ids.filiera.demo.repositories;

import it.cs.unicam.ids.filiera.demo.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdiniRepository extends JpaRepository<Ordine, Long> {

}
