package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.domainModel.Observer;
import it.cs.unicam.ids.filiera.domainModel.Subject;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.util.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bundle implements Subject {

    private final List<Product> products;
    private Long id;
    private User owner;
    private double price;
    private int quantity;
    private Date expiryDate;
    private final List<Observer> observers = new ArrayList<>();
    private Status status;

    public Bundle(List<Product> products, User owner, double price, int quantity, Date expiryDate) {
        this.products = products;
        this.owner = owner;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    /**
     * Method to add a product into this bundle
     * @param p
     */
    public void addProduct(Product p) {
        products.add(p);
    }

    /**
     * Method to remove the product from this bundle
     * @param p
     */
    public void removeProduct(Product p) {
        products.remove(p);
    }

    @Override
    public void attach(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    //Getters and setters methods
    public List<Product> getProducts() {
        return products;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}