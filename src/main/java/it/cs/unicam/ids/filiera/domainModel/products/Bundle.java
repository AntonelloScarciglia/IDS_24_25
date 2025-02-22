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

public class Bundle extends CatalogableItem implements Subject {

    private final List<Product> products;
    private int quantity;

    public Bundle(String name, Double price, User owner, Date expiryDate, List<Product> products, int quantity, Status status) {
        super(name, price, owner, expiryDate, status);
        this.products = products;
        this.quantity = quantity;
    }

    /**
     * Method to add a product into this bundle
     * @param p
     */
    public void addProduct(Product p) {
        ValidationUtils.checkProduct(p);
        ValidationUtils.checkProductIfPresentInBundle(p, this);
        products.add(p);
        System.out.println("Adding product successfully");
    }

    /**
     * Method to remove the product from this bundle
     * @param p
     */
    public void removeProduct(Product p) {
        ValidationUtils.checkProduct(p);
        ValidationUtils.checkProductIfAbsence(p, this);
        products.remove(p);
        System.out.println("Removing product successfully");
    }

    @Override
    public void attach(Observer o) {
        this.getObservers().add(o);
    }

    @Override
    public void detach(Observer o) {
        this.getObservers().remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : getObservers()) {
            o.update(this);
        }
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