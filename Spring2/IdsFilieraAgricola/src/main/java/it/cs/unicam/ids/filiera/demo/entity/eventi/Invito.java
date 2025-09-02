package it.cs.unicam.ids.filiera.demo.entity.eventi;

import it.cs.unicam.ids.filiera.demo.entity.UtenteVerificato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Setter
public class Invito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invitato_id", nullable = false)
    private UtenteVerificato invitato;

    private String messaggio;
    private InvitoStato stato;

    protected Invito() {
    }

    public Invito(Evento evento, UtenteVerificato invitato, String messaggio) {
        this.evento = Objects.requireNonNull(evento);
        this.invitato = Objects.requireNonNull(invitato);
        this.messaggio = messaggio;
        this.stato = InvitoStato.IN_ATTESA;
    }


}
