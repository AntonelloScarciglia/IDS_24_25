package it.cs.unicam.ids.filiera.demo.repositories;

import it.cs.unicam.ids.filiera.demo.entity.eventi.Evento;
import it.cs.unicam.ids.filiera.demo.entity.eventi.Invito;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface InvitoRepository extends JpaRepository<Invito, Long> {

    Optional<Invito> findByEventoIdAndInvitatoId(Long eventoId, Long invId);

    List<Invito> findByInvitatoId(Long invitatoId);

    List<Invito> findByEventoId(Long eventoId);
}
