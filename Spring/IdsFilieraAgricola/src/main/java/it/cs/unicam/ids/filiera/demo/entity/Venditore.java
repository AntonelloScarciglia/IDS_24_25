package it.cs.unicam.ids.filiera.demo.entity;

import it.cs.unicam.ids.filiera.demo.model.Ruolo;
import jakarta.persistence.*;

@Entity
public abstract class Venditore extends UtenteVerificato {

	private String codiceFiscale;
	private boolean attesa = true;

	// Costruttore JPA
	protected Venditore() {}

	public Venditore(String nome, String cognome, String email, String password, String codiceFiscale) {
		super(nome, cognome, email, password);
		this.codiceFiscale = codiceFiscale;
	}

	// Ogni sottoclasse decide come crea i prodotti
	public abstract Prodotto creaProdotto(String nomeProdotto, String categoria, double prezzo);

	// --- Getter & Setter ---
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public boolean isAttesa() {
		return attesa;
	}

	public void setAttesa(boolean attesa) {
		this.attesa = attesa;
	}
}
