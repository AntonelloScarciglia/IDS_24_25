package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BUNDLE")
public class Bundle extends Prodotto {

	@ManyToMany
	@JoinTable(
			name = "bundle_prodotti",
			joinColumns = @JoinColumn(name = "bundle_id"),
			inverseJoinColumns = @JoinColumn(name = "prodotto_id")
	)
	private List<Prodotto> prodotti = new ArrayList<>();
	private boolean isBundle;

	// costruttore  JPA
	protected Bundle() {

	}

	public Bundle(Long venditoreId, String nome, String categoria, BigDecimal prezzo, LocalDate dataScadenza) {
		super(venditoreId, nome, categoria, prezzo, dataScadenza);
	}

	public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

	public boolean getisBundle() { return isBundle;	}

	public void setBundle(boolean bundle) { this.isBundle = bundle; }
}
