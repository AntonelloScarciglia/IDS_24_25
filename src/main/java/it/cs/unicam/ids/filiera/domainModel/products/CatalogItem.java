package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.util.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public abstract class CatalogItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	protected String name;
	protected Double price;
	protected AuthUser owner;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	protected Date expiryDate;
	protected Status status;

	public CatalogItem(String name, Double price, AuthUser owner, Date expiryDate, Status status){
		this.name = name;
		this.price = price;
		this.owner = owner;
		this.expiryDate = expiryDate;
		this.status = status;
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

	public void setStatus(Status status) {
		this.status = status;
		//notifyObservers();
	}

	public abstract String getInfo();
}
