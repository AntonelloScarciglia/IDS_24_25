package it.cs.unicam.ids.filiera.demo.repositories;

import it.cs.unicam.ids.filiera.demo.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdiniRepository extends JpaRepository<Ordine, Long> {

}
