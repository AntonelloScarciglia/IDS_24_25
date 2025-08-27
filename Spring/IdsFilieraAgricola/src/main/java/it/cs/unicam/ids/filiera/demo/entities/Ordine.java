package it.cs.unicam.ids.filiera.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key, autogenerata
    private Long id;

}