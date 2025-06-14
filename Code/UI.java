public interface UI {

	/**
	 * 
	 * @param nome
	 * @param desc
	 * @param prezzo
	 */
	void creaProdotto(int nome, int desc, int prezzo);

	/**
	 * 
	 * @param String
	 */
	void aggiungiCatenaFornitura(int String);

	void RevisioneProdotti();

	void gestisciProdottiCaricati();

	/**
	 * 
	 * @param Prodotto
	 */
	void rimuoviProdotto(int Prodotto);

	/**
	 * 
	 * @param nome
	 * @param data
	 * @param desc
	 */
	void creaEvento(int nome, int data, int desc);

	void IntiaUtentiEventi();

	/**
	 * 
	 * @param evento
	 * @param UtenteBusines
	 */
	void InvitaUtentiBusiness(int evento, int UtenteBusines);

	void controllaInviti();

	void VisualizzaInvito();

	/**
	 * 
	 * @param invito
	 * @param scelta
	 */
	void RispondiInvito(int invito, int scelta);

	void ApportaModifiche();

	/**
	 * 
	 * @param prodotto
	 */
	void prodottoDaModificare(int prodotto);

	/**
	 * 
	 * @param String
	 */
	void modificaInformazioni(int String);

	void getEventiAquirente();

	/**
	 * 
	 * @param evento
	 */
	void selezionaEvento(int evento);

	/**
	 * 
	 * @param evento
	 * @param utente
	 */
	void RiservaEvento(int evento, int utente);

	void creaSessione();

	void autenticarsi();

	/**
	 * 
	 * @param mail
	 * @param psw
	 */
	void Login(int mail, int psw);

	/**
	 * 
	 * @param mail
	 * @param psw
	 */
	void SingUp(int mail, int psw);

	/**
	 * 
	 * @param mail
	 * @param Psw
	 * @param CF
	 * @param role
	 */
	void SingUpBusiness(int mail, int Psw, int CF, int role);

	void visualizzaCarrello();

	void Checkout();

	void gestirePermessi();

	/**
	 * 
	 * @param user
	 */
	void selezionaUtente(int user);

	/**
	 * 
	 * @param utente
	 * @param scelta
	 */
	void gestisci(int utente, int scelta);

	/**
	 * 
	 * @param prodotto
	 */
	void aggiungiAlCarrello(int prodotto);

	/**
	 * 
	 * @param prodotto
	 */
	void selezioneOggetto(int prodotto);

	/**
	 * 
	 * @param Id
	 */
	void ProdottiPosseduti(int Id);

	/**
	 * 
	 * @param prodotto
	 */
	void scegliProdotto(int prodotto);

	/**
	 * 
	 * @param String
	 * @param Prodotto
	 */
	void CondividiProdotto(int String, int Prodotto);

	void CercaEventi();

	/**
	 * 
	 * @param evento
	 */
	void mostraEvento(int evento);

	/**
	 * 
	 * @param String
	 * @param Evento
	 */
	void condividi(int String, int Evento);

}