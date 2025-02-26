package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.observer.UserObserver;
import it.cs.unicam.ids.filiera.util.Status;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Product extends CatalogItem {

	private int quantity;
	private String category;
	@ElementCollection
	private List<Phase> supplyChain;

	public Product(String name, Double price, AuthUser owner, Date expiryDate, Status status, int quantity, String category) {
		super(name, price, owner, expiryDate, status);
		this.quantity = quantity;
		this.category = category;
		this.supplyChain = new ArrayList<>();
		this.attach(new UserObserver());
	}

	public Product() {
		super();
		this.supplyChain = new ArrayList<>();
	}


	/**
	 * Method to add a phase for the product
	 * @param p phase
	 */
	public void addPhase(Phase p) {
		supplyChain.add(p);
	}

	/**
	 * Method to remove a phase of the product
	 * @param p phase
	 */
	public void removePhase(Phase p) {
		if(supplyChain.contains(p)){
			supplyChain.remove(p);
		}else{
			System.out.println("Phase not found");
		}
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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

	@Override
	public String getInfo() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", owner=" + owner +
				", quantity=" + quantity +
				", status=" + status +
				", price=" + price +
				", category='" + category + '\'' +
				", supplyChain=" + supplyChain +
				", expiryDate=" + expiryDate +
				'}';
	}
}