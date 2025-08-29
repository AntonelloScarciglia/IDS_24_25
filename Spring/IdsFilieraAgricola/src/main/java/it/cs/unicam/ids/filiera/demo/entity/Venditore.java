package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public abstract class Venditore extends UtenteVerificato {

	private String codiceFiscale;
	private boolean attesa = true;
    @Id
    private Long id;

	// Costruttore JPA
	protected Venditore() {}

	public Venditore(String nome, String cognome, String email, String password, String codiceFiscale) {
		super(nome, cognome, email, password);
		this.codiceFiscale = codiceFiscale;
	}

	// Ogni sottoclasse decide come crea i prodotti
	public abstract Prodotto creaProdotto(String nomeProdotto, String categoria, BigDecimal prezzo);


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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
