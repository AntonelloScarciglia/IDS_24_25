package it.cs.unicam.ids.filiera.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titolo;

    @Column(length = 4000)
    private String descrizione;

    @Column(nullable = false)
    private String luogo;

    @Column(name = "data_inizio", nullable = false)
    private LocalDateTime dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDateTime dataFine;

    @Column(name = "posti_disponibili")
    private int postiDisponibili;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creatore_id", nullable = false)
    private UtenteVerificato creatore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "evento_partecipanti",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private List<UtenteVerificato> partecipanti = new ArrayList<>();

    // Costruttore richiesto da JPA
    public Evento() {}

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLuogo() {
        return luogo;
    }
    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }
    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }
    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public int getPostiDisponibili() {
        return postiDisponibili;
    }
    public void setPostiDisponibili(int postiDisponibili) {
        this.postiDisponibili = postiDisponibili;
    }

    public UtenteVerificato getCreatore() {
        return creatore;
    }

    public void setCreatore(UtenteVerificato creatore) {
        this.creatore = creatore;
    }

    public List<UtenteVerificato> getPartecipanti() {
        return partecipanti;
    }
    public void setPartecipanti(List<UtenteVerificato> partecipanti) {
        this.partecipanti = partecipanti;
    }

    //true se evento a posti illimitati
    @Transient // non persistente
    public boolean isIllimitato() {
        return postiDisponibili <= 0;
    }

    // Utility per aggiungere un partecipante evitando duplicati
    public void aggiungiPartecipante(UtenteVerificato u) {
        if (u != null && !partecipanti.contains(u)) {
            partecipanti.add(u);
        }
    }
}

