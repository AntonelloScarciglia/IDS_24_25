package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.serviceLayer.ProductService;
import it.cs.unicam.ids.filiera.domainModel.products.*;
import it.cs.unicam.ids.filiera.serviceLayer.UserService;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    /**
     * Retrieves a product by its ID.
     *
     * @param id The product ID.
     * @return A ResponseEntity containing the product with HTTP status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Retrieves all products.
     *
     * @return A ResponseEntity containing the list of all products with HTTP status 200 (OK).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves all pending products.
     *
     * @return A ResponseEntity containing the list of all pending products with HTTP status 200 (OK).
     */
    @GetMapping("/pending")
    public ResponseEntity<List<Product>> getAllPendingProducts() {
        List<Product> products = productService.filterByStatus(Status.PENDING);
        return ResponseEntity.ok(products);
    }

    /**
     * Retrieves all approved products.
     *
     * @return A ResponseEntity containing the list of all approved products with HTTP status 200 (OK).
     */
    @GetMapping("/approved")
    public ResponseEntity<List<Product>> getAllApprovedProducts() {
        List<Product> products = productService.filterByStatus(Status.APPROVED);
        return ResponseEntity.ok(products);
    }

    /**
     * Approves a pending product.
     *
     * @param productId The ID of the product to approve.
     * @param userId    The ID of the curator (user) approving the product.
     * @return A ResponseEntity with a success message.
     */
    @PostMapping("/approve") // Maps POST requests on /product/approve
    public ResponseEntity<String> approveProduct(@RequestParam Long productId, Long userId) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        // UserID should be retrievet automatically from SpringSecurity or HTTP Session
        AuthUser curator = userService.getUserById(userId);
        productService.approve(product, curator);
        return ResponseEntity.ok("Product approved successfully.");
    }

    /**
     * Rejects a pending product.
     *
     * @param productId The ID of the product to reject.
     * @param reason    The reason for rejection.
     * @param userId    The ID of the curator (user) rejecting the product.
     * @return A ResponseEntity with a success message.
     */
    @PostMapping("/reject") // Maps POST requests on /product/reject
    public ResponseEntity<String> rejectProduct(@RequestParam Long productId, @RequestParam String reason, @RequestParam Long userId) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

		// UserID should be retrievet automatically from SpringSecurity or HTTP Session
		AuthUser curator = userService.getUserById(userId);

        productService.reject(product, reason, curator);
        return ResponseEntity.ok("Product rejected successfully.");
    }

	/**
	 * Retrieves the info of a product.
	 *
	 * @param productId The ID of the product.
	 * @return A ResponseEntity containing the product info as a string.
	 */
	@GetMapping("/info/{productId}") // Maps GET requests on /product/info/{productId}
	public ResponseEntity<String> getProductInfo(@PathVariable Long productId) {
		Product product = productService.getProduct(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product.getInfo());
	}

	/**
	 * Creates a new product.
	 *
	 * @param name       The name of the product.
	 * @param ownerId    The ID of the owner/creator.
	 * @param quantity   The quantity of the product.
	 * @param price      The price of the product.
	 * @param expiryDate The expiry date of the product.
	 * @param category   The category of the product.
	 * @return A ResponseEntity containing the created product.
	 */
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestParam String name,
												  Long ownerId,
												 @RequestParam int quantity,
												 @RequestParam Double price,
												 @RequestParam Date expiryDate,
												 @RequestParam String category) {
		// UserID should be obtained from SpringSecurity or HTTP session
		AuthUser owner = userService.getUserById(ownerId);
		Product product = productService.createProduct(name, owner, quantity, price, expiryDate, category);
		return ResponseEntity.ok(product);
	}


    /**
     * Method to add a new phase to a product
     *
     * @param productId Long
     * @param p         Phase
     */
    public void addPhaseToProduct(Long productId, Phase p) {
        // TODO - implement ProductController.addContentToProduct
		throw new UnsupportedOperationException();
    }

    /**
     * Method to add a content to a product
     *
     * @param productId
     * @param c
     */
    public void addContentToProduct(Long productId, Content c) {
        // TODO - implement ProductController.addContentToProduct
        throw new UnsupportedOperationException();
    }

    public void approveContent() {
        // TODO - implement ProductController.approveContent
        throw new UnsupportedOperationException();
    }

    public void rejectContent() {
        // TODO - implement ProductController.rejectContent
        throw new UnsupportedOperationException();
    }

    public void apportaModifiche() {
        // TODO - implement ProductController.apportaModifiche
        throw new UnsupportedOperationException();
    }

    /**
     * @param user AuthUser
     * @return List of products
     */
    public List<Product> filterByOwner(AuthUser user) {
        return productService.filterByCreator(user);
    }

    /**
     * @param c
     */
    public void ToStringProductInfo(CatalogItem c) {
        // TODO - implement ProductController.ToStringProductInfo
        throw new UnsupportedOperationException();
    }

    public void updateProduct() {
        // TODO - implement ProductController.updateProduct
        throw new UnsupportedOperationException();
    }

    /**
     * @param data
     * @param Product
     */
    public void addSupplyChainToProduct(int data, int Product) {
        // TODO - implement ProductController.addSupplyChainToProduct
        throw new UnsupportedOperationException();
    }

    /**
     * @param c
     * @param choice
     */
    public void reviewItem(CatalogItem c, boolean choice) {
        Product product = productService.getProduct(c.getId());
        if (choice) {
            productService.approve(product, product.getOwner());
        } else {
            productService.reject(product, "Product not valid for the approvement", product.getOwner());
        }
    }

    /**
     * Method to remove a product from the repository
     *
     * @param product Product
     */
    public void removeProduct(Product product) {
        productService.deleteProduct(product);
    }

}