package it.cs.unicam.ids.filiera.demo.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRASFORMATORE")
public class Trasformatore extends Venditore {

	protected Trasformatore() {
		// costruttore richiesto da JPA
	}

	public Trasformatore(String nome, String cognome, String email, String password, String codiceFiscale) {
		super(nome, cognome, email, password,codiceFiscale);
	}

	@Override
	public Prodotto creaProdotto(String nomeProdotto, String categoria, double prezzo) {
		return null;
	}
}
