package it.cs.unicam.ids.filiera.demo.controllers;


import it.cs.unicam.ids.filiera.demo.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/prodotti")
// Il controller ProdottoController gestisce le richieste HTTP relative ai prodotti.
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;


}
