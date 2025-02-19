package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Observer;
import it.cs.unicam.ids.filiera.domainModel.Subject;
import it.cs.unicam.ids.filiera.domainModel.Users.User;

import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Document(collection = "products")
public class Product implements Subject {

	@Id
	private Long id;
	private String name;
	private String category;
	private Status status;
	private List<Phase> supplyChain;
	private User owner;
	private double price;
	private int quantity;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expiryDate;
	private List<Observer> observers;

	/**
	 * Construct of the Product class
	 * @param id
	 * @param name
	 * @param category
	 * @param status
	 * @param owner
	 * @param price
	 * @param quantity
	 * @param expiryDate
	 */
	public Product(Long id, String name, String category, Status status, User owner, double price, int quantity, Date expiryDate ) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.status = status;
		this.supplyChain = new ArrayList<>();
		this.owner = owner;
		this.price = price;
		this.quantity = quantity;
		this.expiryDate = expiryDate;
		this.observers = new ArrayList<>();
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
		if(observers.contains(o)){
			observers.add(o);
		}
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : observers){
			observer.update(this);
		}
	}



	/**
	 * Getter and Setter methods
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<Observer> getObservers() {
		return observers;
	}

	public void setObservers(List<Observer> observers) {
		this.observers = observers;
	}
}