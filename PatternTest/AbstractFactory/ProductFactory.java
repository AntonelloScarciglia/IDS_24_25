package AbstractFactory;

import Products.Product;
import Users.User;

public abstract class ProductFactory {
    public abstract Product create(User creator, String name, String category, String description,double price);
}
