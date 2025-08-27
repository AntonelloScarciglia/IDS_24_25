package it.cs.unicam.ids.filiera.demo.model;

import it.cs.unicam.ids.filiera.demo.factory.Venditore;
import jakarta.persistence.*;

@Entity
@Table(name = "venditori")
public class VenditoreConcreto implements Venditore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;
    private String cognome;

    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    private String codiceFiscale;

    // se è in attesa di approvazione, ad es.
    private Boolean attesa;

    public VenditoreConcreto() {}

    public VenditoreConcreto(String nome, String cognome, String email, Ruolo ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.ruolo = ruolo;
        this.attesa = Boolean.TRUE;
    }

    // Implementare factory
    @Override
    public Prodotto creaProdotto() {
        return new Prodotto();
    }

    // getter/setter
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Ruolo getRuolo() { return ruolo; }
    public void setRuolo(Ruolo ruolo) { this.ruolo = ruolo; }
    public String getCodiceFiscale() { return codiceFiscale; }
    public void setCodiceFiscale(String codiceFiscale) { this.codiceFiscale = codiceFiscale; }
    public Boolean getAttesa() { return attesa; }
    public void setAttesa(Boolean attesa) { this.attesa = attesa; }
}

