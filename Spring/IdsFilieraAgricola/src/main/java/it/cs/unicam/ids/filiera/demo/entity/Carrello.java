package it.cs.unicam.ids.filiera.demo.entity;

import java.util.List;

public class Carrello {

	private List<Prodotto> ListaProdotti;

    public Carrello(List<Prodotto> listaProdotti) {
        ListaProdotti = listaProdotti;
    }

	public List<Prodotto> getProdotti() {
		return ListaProdotti;
	}

}