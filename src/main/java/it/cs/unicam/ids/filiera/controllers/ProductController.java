package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.serviceLayer.ProductService;
import it.cs.unicam.ids.filiera.domainModel.products.*;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Method to retrieve a product from the repository
	 * @param id Long
	 * @return Product product
	 */
	@GetMapping("/{id]")
	public Product getProduct(Long id) {
		return productService.getProduct(id);
	}

	/**
	 * Method to retrieve all the product from the repository
	 * @return List of all products
	 */
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Method to retrieve all the pending products from the repository
	 * @return List of all pending products
	 */
	@GetMapping("/pending")
	public List<Product> getAllPendingProducts() {
		return productService.filterByStatus(Status.PENDING);
	}

	/**
	 * Method to retrieve all the approved products from the repository
	 * @return List of all approved products
	 */
	@GetMapping("/approved")
	public List<Product> getAllApprovedProducts() {
		return productService.filterByStatus(Status.APPROVED);
	}

	/**
	 * Method to approve a pending product
	 * @param productId Long
	 * @param user AuthUser
	 */
	public void approveProduct(Long productId, AuthUser user) {
		productService.approve(productService.getProduct(productId), user);
	}

	/**
	 * Method to reject a pending product
	 * @param productId Long
	 * @param reason String
	 * @param user AuthUser
	 */
	public void rejectProduct(Long productId, String reason, AuthUser user) {
		productService.reject(productService.getProduct(productId),reason, user);
	}

	/**
	 * Method to retrieve the info of a product
	 * @param productId Long
	 * @return String of product's info
	 */
	public String getProductInfo(Long productId) {
		return productService.getProduct(productId).getInfo();
	}

	/**
	 *Method to create a product
	 * @param name String
	 * @param owner AuthUser
	 * @param quantity int
	 * @param price Double
	 * @param expiryDate Date
	 * @param category String
	 * @return Product product
	 */
	public Product createProduct(String name, AuthUser owner, int quantity, Double price, Date expiryDate, String category) {
		return productService.createProduct(name, owner, quantity, price, expiryDate, category);
	}

	/**
	 * Method to add a new phase to a product
	 * @param productId Long
	 * @param p Phase
	 */
	public void addPhaseToProduct(Long productId, Phase p) {
		this.getProduct(productId).addPhase(p);
	}

	/**
	 * Method to add a content to a product
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
	 * 
	 * @param user AuthUser
	 * @return List of products
	 */
	public List<Product> filterByOwner(AuthUser user) {
		return productService.filterByCreator(user);
	}

	/**
	 * 
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
	 * 
	 * @param data
	 * @param Product
	 */
	public void addSupplyChainToProduct(int data, int Product) {
		// TODO - implement ProductController.addSupplyChainToProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 * @param choice
	 */
	public void reviewItem(CatalogItem c, boolean choice) {
		Product product = productService.getProduct(c.getId());
		if(choice) {
			productService.approve(product, product.getOwner());
		} else {
			productService.reject(product, "Product not valid for the approvement", product.getOwner());
		}
	}

	/**
	 * Method to remove a product from the repository
	 * @param product Product
	 */
	public void removeProduct(Product product) {
		productService.deleteProduct(product);
	}

}