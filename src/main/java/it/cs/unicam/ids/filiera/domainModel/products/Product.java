package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Observer;
import it.cs.unicam.ids.filiera.domainModel.Subject;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import java.util.Date;
import java.util.List;

public class Product implements Subject {

	private int id;
	private String name;
	private String category;
	private List<Phase> supplyChain;
	private User owner;
	private double price;
	private int quantity;
	private Date expiryDate;
	private List<Observer> observers;

	/**
	 * 
	 * @param p
	 */
	public void addPhase(Phase p) {
		// TODO - implement Product.addPhase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public void removePhase(Phase p) {
		// TODO - implement Product.removePhase
		throw new UnsupportedOperationException();
	}

	@Override
	public void attach(Observer o) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void detach(Observer o) {
		// TODO
		throw new UnsupportedOperationException();
	}

	@Override
	public void notifyObservers() {
		// TODO
		throw new UnsupportedOperationException();
	}
}