package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.observer.Observer;
import it.cs.unicam.ids.filiera.domainModel.observer.Subject;
import it.cs.unicam.ids.filiera.domainModel.observer.UserObserver;
import it.cs.unicam.ids.filiera.util.Status;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Abstract base class for catalog items.
 */
@MappedSuperclass
public abstract class CatalogItem implements Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String name;
	protected Double price;
	@ManyToOne
	protected AuthUser owner;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	protected Date expiryDate;
	protected Status status;
	@Transient
	private final List<Observer> observers;

	protected CatalogItem() {
		this.observers = new ArrayList<>();
		this.attach(new UserObserver());
	}

	public CatalogItem(String name, Double price, AuthUser owner, Date expiryDate, Status status){
		this.name = name;
		this.price = price;
		this.owner = owner;
		this.expiryDate = expiryDate;
		this.status = Status.PENDING;
		this.observers = new ArrayList<>();
		this.attach(new UserObserver());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public AuthUser getOwner() {
		return owner;
	}

	public void setOwner(AuthUser owner) {
		this.owner = owner;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Status getStatus() {
		return status;
	}

	/**
	 * Sets the status and notifies observers.
	 * @param status the new status
	 */
	public void setStatus(Status status) {
		this.status = status;
		notifyObservers();
	}

	@Override
	public void attach(Observer o) {
		observers.add(o);
	}

	@Override
	public void detach(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(this);
		}
	}


	public abstract String getInfo();
}
