package it.cs.unicam.ids.filiera.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key, autogenerata
    private Long id;

}