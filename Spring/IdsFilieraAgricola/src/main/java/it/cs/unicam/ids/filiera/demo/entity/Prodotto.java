package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "prodotti")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long venditoreId;     // semplice FK logica, niente @ManyToOne
	private String nome;
	private String categoria;
	private BigDecimal  prezzo;

	@Temporal(TemporalType.DATE)
	private LocalDate  dataScadenza;

	public Prodotto() {}

	protected Prodotto(Long venditoreId, String nome, String categoria, BigDecimal prezzo, LocalDate  dataScadenza) {
		this.venditoreId = venditoreId;
		this.nome = nome;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.dataScadenza = dataScadenza;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Prodotto)) return false;
		Prodotto that = (Prodotto) o;
		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() { return Objects.hash(getClass()); }
}

