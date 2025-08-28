package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordini")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// acquirente = utente che ha fatto l'ordine
	@ManyToOne(optional = false)
	@JoinColumn(name = "acquirente_id")
	private UtenteVerificato acquirente;

	// relazione con Carrello (se lo vuoi salvare come entità)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "carrello_id", referencedColumnName = "id")
	private Carrello carrello;

	private LocalDate dataOrdine;

	// costruttori
	public Ordine() {}

	public Ordine(UtenteVerificato acquirente, Carrello carrello) {
		this.acquirente = acquirente;
		this.carrello = carrello;
		this.dataOrdine = LocalDate.now();
	}

	// getters e setters
	public Long getId() { return id; }

	public UtenteVerificato getAcquirente() { return acquirente; }
	public void setAcquirente(UtenteVerificato acquirente) { this.acquirente = acquirente; }

	public Carrello getCarrello() { return carrello; }
	public void setCarrello(Carrello carrello) { this.carrello = carrello; }

	public LocalDate getDataOrdine() { return dataOrdine; }
	public void setDataOrdine(LocalDate dataOrdine) { this.dataOrdine = dataOrdine; }

	// toString
	@Override
	public String toString() {
		return "Ordine #" + id +
				" - Acquirente: " + acquirente.getEmail() +
				" - Data: " + dataOrdine +
				" - Prodotti: " + (carrello != null ? carrello.getProdotti().size() : 0);
	}
}
