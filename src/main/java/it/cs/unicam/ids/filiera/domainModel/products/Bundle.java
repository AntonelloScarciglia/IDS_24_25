package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.observer.UserObserver;
import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Bundle represents a collection of products sold together.
 */
@Entity
public class Bundle extends CatalogItem {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
    private int quantity;

    public Bundle(String name, Double price, AuthUser owner, Date expiryDate, List<Product> products, int quantity, Status status) {
        super(name, price, owner, expiryDate, status);
        this.products = products;
        this.quantity = quantity;
    }

    public Bundle() {

    }

    /**
     * Method to add a product into this bundle
     *
     * @param p the product to add.
     */
    public void addProduct(Product p) {
        ValidationUtils.checkProduct(p);
        ValidationUtils.checkProductIfPresentInBundle(p, this);
        products.add(p);
        System.out.println("Adding product successfully");
    }

    /**
     * Removes a product from the bundle.
     * @param p the product to remove.
     */
    public void removeProduct(Product p) {
        ValidationUtils.checkProduct(p);
        ValidationUtils.checkProductIfAbsence(p, this);
        products.remove(p);
        System.out.println("Removing product successfully");
    }

    //Getters and setters methods
    public List<Product> getProducts() {
        return products;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getInfo() {
        return "Bundle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", owner=" + owner +
                ", status=" + status +
                ", price=" + price +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                '}';
    }
}