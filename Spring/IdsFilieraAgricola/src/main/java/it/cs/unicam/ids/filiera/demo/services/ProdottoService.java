package it.cs.unicam.ids.filiera.demo.services;

import it.cs.unicam.ids.filiera.demo.dtos.ProdottoDTO;
import it.cs.unicam.ids.filiera.demo.dtos.ProdottoMapper;
import it.cs.unicam.ids.filiera.demo.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Il servizio ProdottoService gestisce la logica di business relativa ai prodotti.
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;



}
