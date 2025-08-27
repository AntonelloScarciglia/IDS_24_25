package it.cs.unicam.ids.filiera.demo.model;


import it.cs.unicam.ids.filiera.demo.factory.Venditore;
import it.cs.unicam.ids.filiera.demo.model.VenditoreConcreto;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prodotti")
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nome;

	private String categoria;

	private double prezzo;

	@ManyToOne
	@JoinColumn(name = "venditore_id") // FK verso tabella venditori
	private VenditoreConcreto possessore;

	@Temporal(TemporalType.DATE)
	private Date dataScadenza;

	// Costruttori
	public Prodotto() {}

	public Prodotto(String nome, String categoria, double prezzo, VenditoreConcreto possessore, Date dataScadenza) {
		this.nome = nome;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.possessore = possessore;
		this.dataScadenza = dataScadenza;
	}

	// Getter e Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public Venditore getPossessore() {
		return possessore;
	}

	public void setPossessore(VenditoreConcreto possessore) {
		this.possessore = possessore;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
}
