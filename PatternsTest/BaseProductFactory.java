package AbstractFactory;
import Products.BaseProduct;
import Products.Product;
import example1.User;
import java.time.LocalDate;
import java.util.List;

public class BaseProductFactory extends ProductFactory{

    @Override
    public Product create(User creator, String name, String category, String description, double price) {
        return null;
        /*
        return new BaseProduct(User creator,
        String name
        String catgory;
        String description,
        double price,
        boolean status,
        List<String> cultivationMethods,
        List<String> certifications,
        int quantity,
        LocalDate expiryDate);
        */
    }
}
