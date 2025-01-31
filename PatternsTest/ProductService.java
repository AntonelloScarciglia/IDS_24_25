package Products;

import Builder.BaseProductBuilder;
import example1.Role;
import example1.User;

import java.time.LocalDate;
import java.util.List;

public class ProductService {
    public BaseProduct createBaseProduct(User creator, String name, String category,
                                     String description, double price,
                                     LocalDate expiryDate, int quantity,
                                     List<String> cultivationMethods, List<String> certifications) throws IllegalAccessException {

        if (!(creator.getRole().equals(Role.PRODUCER))) {
            throw new IllegalAccessException("User does not have permission to create a product");
        }

        return new BaseProductBuilder()
                .setCreator(creator)
                .setName(name)
                .setCategory(category)
                .setDescription(description)
                .setPrice(price)
                .setExpiryDate(expiryDate)
                .setQuantity(quantity)
                .setCultivationMethods(cultivationMethods)
                .setCertification(certifications)
                .build();
    }
}
