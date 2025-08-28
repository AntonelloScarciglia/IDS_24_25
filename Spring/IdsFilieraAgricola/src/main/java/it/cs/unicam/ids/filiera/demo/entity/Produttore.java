package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PRODUTTORE")
public class Produttore extends Venditore {

	protected Produttore() {
		// costruttore JPA
	}

	@Override
	public Prodotto creaProdotto(String nomeProdotto, String categoria, double prezzo) {
		return null;
	}

	public Produttore(String nome, String cognome, String email, String password, String codiceFiscale) {
		super(nome, cognome, email, password, codiceFiscale);
	}
}
