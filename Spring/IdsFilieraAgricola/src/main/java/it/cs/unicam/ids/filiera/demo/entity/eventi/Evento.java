package it.cs.unicam.ids.filiera.demo.entity.eventi;

import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity

@Table(name = "eventi")
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

    // capienza massima. Se <=0 posti illimitati
    @Column(name = "capienza_max")
    private int capienzaMax;

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
    public Evento() {
    }

    //true se evento a posti illimitati
    @Transient // non persistente
    public boolean isIllimitato() {
        return capienzaMax <= 0;
    }


    // Utility per aggiungere un partecipante evitando duplicati e rispettando capienza massima
    public void aggiungiPartecipante(UtenteVerificato u) {
        if (u == null) throw new IllegalStateException("Utente nullo");
        if (contienePartecipante(u)) {
            return;
        }
        if (!isIllimitato() && partecipanti.size() >= capienzaMax) {
            throw new IllegalStateException("Evento pieno, impossibile aggiungere partecipante");
        }
        partecipanti.add(u);
    }

    public void rimuoviPartecipante(UtenteVerificato u) {
        if (u == null) throw new IllegalStateException("Utente nullo");
        partecipanti.removeIf(p -> p.getId().equals(u.getId()));
    }

    public boolean contienePartecipante(UtenteVerificato u) {
        return u != null && partecipanti.stream().anyMatch(p -> p.getId().equals(u.getId()));
    }


    public int getPostiRimasti() {
        return isIllimitato() ? Integer.MAX_VALUE
                : Math.max(0, capienzaMax - partecipanti.size());
    }


    public void setPostiDisponibili(int nuovi) {
        if (nuovi <= 0) {
            this.capienzaMax = 0;
            return; // illimitato
        }
        if (nuovi < partecipanti.size()) {
            throw new IllegalStateException(
                    "Capienza (" + nuovi + ") < partecipanti già iscritti (" + partecipanti.size() + ")"
            );
        }
        this.capienzaMax = nuovi;
    }
}

