package it.cs.unicam.ids.filiera.demo.services;


import it.cs.unicam.ids.filiera.demo.dtos.RegistrazioneDTO;
import it.cs.unicam.ids.filiera.demo.dtos.UtenteDTO;
import it.cs.unicam.ids.filiera.demo.dtos.UtenteMapper;
import it.cs.unicam.ids.filiera.demo.entity.*;
import it.cs.unicam.ids.filiera.demo.factory.FactoryUtente;
import it.cs.unicam.ids.filiera.demo.factory.Ruolo;
import it.cs.unicam.ids.filiera.demo.model.Sessione;
import it.cs.unicam.ids.filiera.demo.repositories.UtenteRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.cs.unicam.ids.filiera.demo.repositories.OrdineRepository;


import java.util.List;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final ProdottoService prodottoService;
    private final GestionaleService gestionaleService;
    private final OrdineRepository ordineRepository;


    @Autowired
    public UtenteService(GestionaleService gestionaleService,
                         UtenteRepository utenteRepository,
                         ProdottoService prodottoService,
                         OrdineRepository ordineRepository) {
        this.gestionaleService = gestionaleService;
        this.utenteRepository = utenteRepository;
        this.prodottoService = prodottoService;
        this.ordineRepository = ordineRepository;
    }

    public UtenteDTO registraUtente(RegistrazioneDTO dto) {
        // Utente già registrato
        if (utenteRepository.findByEmail(dto.email()).isPresent())
            throw new IllegalArgumentException("Email già in uso");

        // Utente non registrato
        UtenteVerificato utenteDaRegistrare;
        switch (dto.tipo().toUpperCase()) {
            case "PRODUTTORE", "TRASFORMATORE", "DISTRIBUTORE", "ACQUIRENTE", "ANIMATORE" -> {
                utenteDaRegistrare = FactoryUtente.createUser(Ruolo.valueOf(
                        dto.tipo().toUpperCase()),
                        dto.nome(),
                        dto.cognome(),
                        dto.email(),
                        dto.password(),
                        dto.codiceFiscale());
                utenteRepository.save(utenteDaRegistrare);
            }
            default -> throw new IllegalArgumentException("Tipo utente non valido");
        }
        return UtenteMapper.toDto(utenteDaRegistrare);
    }

    public Sessione login(String email, String password) {
        UtenteVerificato utente = utenteRepository.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException("Email non registrata."));

        if (!utente.getPassword().equals(password))
            throw new IllegalArgumentException("Password errata.");

        return new Sessione(utente);
    }

    public UtenteVerificato visualizzaUtente(Long id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato con ID: " + id));
    }

    public List<Prodotto> prodottiPosseduti(Long idVenditore) {
        return prodottoService.getProdotti(idVenditore);
    }

    public void loginFittizio(HttpSession session) {
        Acquirente utenteFinto = new Acquirente("Mario", "Rossi", "mario@example.com", "password");
        utenteFinto = utenteRepository.save(utenteFinto);

        session.setAttribute("utente", utenteFinto);

        Sessione s = gestionaleService.newSessione(session); // usa metodo pubblico
        s.setUtente(utenteFinto);
        session.setAttribute(GestionaleService.SESSIONE_KEY, s);
    }

    public void loginAnimatoreFittizio(HttpSession session) {
        UtenteVerificato animatoreFinto = FactoryUtente.createUser(Ruolo.ANIMATORE,
                "Fabrizio",
                "Romano",
                "fabrizioromano@example.com",
                "psw",
                null);
        utenteRepository.save(animatoreFinto);

        session.setAttribute("animatore", animatoreFinto);

        Sessione s = gestionaleService.newSessione(session); // usa metodo pubblico
        s.setUtente(animatoreFinto);
        session.setAttribute(GestionaleService.SESSIONE_KEY, s);
    }

    public void loginVenditoreFittizio(HttpSession session) {
        UtenteVerificato venditoreFinto = FactoryUtente.createUser(Ruolo.PRODUTTORE,
                "Nico",
                "Schira",
                "nicoschira@example.com",
                "psw",
                null);
        utenteRepository.save(venditoreFinto);
        session.setAttribute("utente", venditoreFinto);
        Sessione s = gestionaleService.newSessione(session); // usa metodo pubblico
        s.setUtente(venditoreFinto);
        session.setAttribute(GestionaleService.SESSIONE_KEY, s);
    }


    public List<Ordine> ordiniUtente(HttpSession session) {
        Sessione s = gestionaleService.newSessione(session); // oppure getOrCreate
        UtenteVerificato utente = s.getUtente();

        if (utente == null)
            throw new IllegalStateException("Utente non presente nella sessione");

        return ordineRepository.findByAcquirenteId(utente.getId());

    }

    public List<String> visualizzaNotifiche(UtenteVerificato utenteCorrente) {
        return utenteRepository.findById(utenteCorrente.getId())
                .get()
                .getNotifiche()
                .stream()
                .toList();
    }

    public String svuotaNotifiche(UtenteVerificato utenteCorrente) {
        UtenteVerificato utente = utenteRepository.findById(utenteCorrente.getId())
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        utente.getNotifiche().clear();
        utenteRepository.save(utente);
        return "Lista notifiche svuotata";
    }

    public List<UtenteDTO> visualizzaVenditori() {
        return utenteRepository.findAllVenditori()
                .stream()
                .map(UtenteMapper::toDto)
                .toList();

    }

    public List<UtenteDTO> visualizzaTuttiUtenti() {
        return utenteRepository.findAll()
                .stream()
                .map(UtenteMapper::toDto)
                .toList();
    }
}



