package Builder;

import Products.Product;
import Users.User;

public interface ProductBuilder {
    ProductBuilder setName(String name);
    ProductBuilder setCreator(User creator);
    ProductBuilder setCategory(String category);
    ProductBuilder setDescription(String description);
    ProductBuilder setPrice(double price);
    ProductBuilder setStatus(boolean status);
    Product build();
}
