package Products;

import example1.User;

public abstract class Product {
    private User creator;
    private String name;
    private String category;
    private String description;
    private double price;
    private boolean status;

    public Product(User creator, String name, String category, String description, double price) {
        this.creator = creator;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.status = false;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isApproved() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    abstract public ProductType getType();

    @Override
    public String toString() {
        return "Product{" +
                "creator=" + creator +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
