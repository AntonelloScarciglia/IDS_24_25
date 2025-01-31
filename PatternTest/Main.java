import Products.Product;
import Products.ProductService;
import Users.Role;
import Users.User;

import java.time.LocalDate;
import java.util.List;

public class Main  {
    public static void main(String[] args) throws IllegalAccessException {

        User user = new User("Pippo");
        user.setRole(Role.PRODUCER);
        ProductService ps = new ProductService();

        Product product = ps.createBaseProduct(
                user,
                "Uva",
                "Frutta",
                "uva coltivata nelle Marche",
                10,
                LocalDate.of(2025, 10,10),
                50,
                List.of("biologico", "OGM"),
                List.of("Rainforest Alliance"));

        System.out.println(product.toString());
    }

}
