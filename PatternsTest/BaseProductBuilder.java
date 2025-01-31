package Builder;

import Products.BaseProduct;
import Products.Product;
import Products.ProductType;
import example1.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BaseProductBuilder implements ProductBuilder {
    private User creator;
    private String name;
    private String category;
    private String description;
    private double price;
    private boolean status;
    private ProductType type;
    private LocalDate expiryDate;
    private int quantity;
    private List<String> cultivationMethods = new ArrayList<>();
    private List<String> certifications = new ArrayList<>();


    @Override
    public BaseProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public BaseProductBuilder setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    @Override
    public BaseProductBuilder setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public BaseProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public BaseProductBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public BaseProductBuilder setStatus(boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public BaseProduct build() {
        return new BaseProduct(creator, name, category, description, price, status, expiryDate, quantity, cultivationMethods, certifications);
    }

    public BaseProductBuilder setCultivationMethods(List<String> cultivationMethods) {
        this.cultivationMethods = cultivationMethods;
        return this;
    }

    public BaseProductBuilder setCertification(List<String> certifications) {
        this.certifications = certifications;
        return this;
    }

    public BaseProductBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public BaseProductBuilder setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

}
