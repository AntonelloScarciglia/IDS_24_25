package it.cs.unicam.ids.filiera.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Esclude i campi nulli dalla serializzazione JSON
public class ProdottoDTO {

}
