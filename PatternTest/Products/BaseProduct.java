package Products;

import Users.User;
import java.time.LocalDate;
import java.util.List;

public class BaseProduct extends Product {
    private List<String> cultivationMethods;
    private List<String> certifications;
    private int quantity;
    private LocalDate expiryDate;

    public BaseProduct(User creator, String name, String category,
                        String description, double price, boolean status,
                        LocalDate expiryDate, int quantity,
                        List<String> cultivationMethods, List<String> certifications) {

        super(creator, name, category, description, price);
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.cultivationMethods = cultivationMethods;
        this.certifications = certifications;
    }

    public List<String> getCultivationMethods() {
        return cultivationMethods;
    }

    public void setCultivationMethods(List<String> cultivationMethods) {
        this.cultivationMethods = cultivationMethods;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<String> certifications) {
        this.certifications = certifications;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "cultivationMethods=" + cultivationMethods +
                ", certifications=" + certifications +
                ", quantity=" + quantity +
                ", expiryDate=" + expiryDate +
                '}';
    }

    @Override
    public ProductType getType() {
        return ProductType.BASE;
    }
}
