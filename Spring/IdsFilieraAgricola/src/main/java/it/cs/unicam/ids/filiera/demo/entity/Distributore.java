package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DISTRIBUTORE")
public class Distributore extends Venditore {

	protected Distributore() {
		// costruttore JPA
	}
	public Distributore(String nome, String cognome, String email, String password, String codiceficale) {
		super(nome, cognome, email, password, codiceficale);
	}

	@Override
	public Prodotto creaProdotto(String nomeProdotto, String categoria, double prezzo) {
		return null;
	}


}
