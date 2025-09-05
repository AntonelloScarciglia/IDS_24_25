package it.cs.unicam.ids.filiera.demo.entity;

import it.cs.unicam.ids.filiera.demo.observer.Notifica;
import it.cs.unicam.ids.filiera.demo.observer.Observer;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "prodotti")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
public class Prodotto implements Notifica {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "venditore_id")
	private Long venditoreId;

	private String nome;
	private String categoria;

	@Column(precision = 12, scale = 2)
	private BigDecimal prezzo;

	@Column(nullable = false)
	private boolean confermato = false; // default: non confermato

	@Column(name = "data_scadenza")
	private LocalDate dataScadenza;

	@Column(name = "attesa")
	private boolean attesa = true; // default: in attesa

	@Transient
	private List<Observer> observers = new ArrayList<>();

	// Costruttore JPA
	public Prodotto() {

	}

	public Prodotto(Long venditoreId, String nome, String categoria, BigDecimal prezzo, LocalDate dataScadenza) {
		this.venditoreId = venditoreId;
		this.nome = nome;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.dataScadenza = dataScadenza;
		this.attesa = true;
		this.confermato = false;
	}


	// Getter / Setter
	public Long getId() { return id; }

	public Long getVenditoreId() { return venditoreId; }
	public void setVenditoreId(Long venditoreId) { this.venditoreId = venditoreId; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getCategoria() { return categoria; }
	public void setCategoria(String categoria) { this.categoria = categoria; }

	public BigDecimal getPrezzo() { return prezzo; }
	public void setPrezzo(BigDecimal prezzo) { this.prezzo = prezzo; }

	public LocalDate getDataScadenza() { return dataScadenza; }
	public void setDataScadenza(LocalDate dataScadenza) { this.dataScadenza = dataScadenza; }

	public boolean isAttesa() { return attesa; }
	public void setAttesa(boolean attesa) { this.attesa = attesa; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Prodotto other)) return false;
		return id != null && id.equals(other.id);
	}

	public boolean isConfermato() {
		return confermato;
	}

	public void setConfermato(boolean confermato) {
		this.confermato = confermato;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass(), id);
	}

	@Override
	public void sub(Observer o) {

	}

	@Override
	public void unsub(Observer o) {

	}

	@Override
	public void notifyObservers(String message) {

	}
}

