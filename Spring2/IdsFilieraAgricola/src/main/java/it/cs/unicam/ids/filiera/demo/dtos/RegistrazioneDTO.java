package it.cs.unicam.ids.filiera.demo.dtos;

public record RegistrazioneDTO(
        String nome,
        String cognome,
        String email,
        String password,
        String tipo,            // "ACQUIRENTE" oppure "VENDITORE"
        String codiceFiscale    // Obbligatorio solo se tipo = "VENDITORE"
) {}
