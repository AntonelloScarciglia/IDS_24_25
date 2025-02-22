package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Observer;
import it.cs.unicam.ids.filiera.domainModel.Subject;
import it.cs.unicam.ids.filiera.domainModel.Users.User;

import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;

import java.util.Date;
import java.util.List;

public class Product extends CatalogableItem implements Subject  {

	private int quantity;
	private String category;
	private List<Phase> supplyChain;

	/**
	 * Construct of the Product class
	 * @param name
	 * @param category
	 * @param status
	 * @param owner
	 * @param price
	 * @param quantity
	 * @param expiryDate
	 */
	public Product(String name, Double price, User owner, Date expiryDate, String category, Status status, int quantity) {
		super(name, price, owner, expiryDate, status);
		this.category = category;
		this.quantity = quantity;
	}

	/**
	 * Method to add a new Phase for the product
	 * @param p
	 */
	public void addPhase(Phase p) {
		ValidationUtils.checkPhaseToAdd(this.supplyChain, p);
		supplyChain.add(p);
		notifyObservers();
	}

	/**
	 * Method to remove a Phase of the product
	 * @param p
	 */
	public void removePhase(Phase p) {
		ValidationUtils.checkPhaseIfPresent(this.supplyChain, p);
		supplyChain.remove(p);
		notifyObservers();
	}

	/**
	 *Methods for implementing the Observer Design Pattern
	 */
	@Override
	public void attach(Observer o) {
		if(getObservers().contains(o)){
			getObservers().add(o);
		}
	}

	@Override
	public void detach(Observer o) {
		getObservers().remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : getObservers()){
			observer.update(this);
		}
	}

	/**
	 * Getter and Setter methods
	 */
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Phase> getSupplyChain() {
		return supplyChain;
	}

	public void setSupplyChain(List<Phase> supplyChain) {
		this.supplyChain = supplyChain;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getInfo() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", owner=" + owner +
				", status=" + status +
				", price=" + price +
				", quantity=" + quantity +
				", expiryDate=" + expiryDate +
				", category='" + category + '\'' +
				", supplyChain=" + supplyChain +
				'}';
	}
}