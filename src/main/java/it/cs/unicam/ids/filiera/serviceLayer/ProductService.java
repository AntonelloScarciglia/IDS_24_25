package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Product;

import it.cs.unicam.ids.filiera.repositories.ProductRepository;

import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;

	/**
     * method to retrieve all products from the repository
     * @return List of all products
     */
    public List<Product> getAllProducts() {
        return (List<Product>) productRepo.findAll();
    }

	/**
	 * method to retrieve a products from the repository
	 * @param id Long
	 * @return Product
	 */
	public Product getProduct(Long id){
		return productRepo.findById(id).orElse(null);
	}

	/**
     * method to save a product in the repository
     * @param product Product
     */
	public void saveProduct(Product product) {
		productRepo.save(product);
	}

	/**
	 * Method to retrieve all products from the repository about the specified owner
	 * @param creator User
	 * @return List of all products of the specified owner
	 * @throws NullPointerException if creator is null
	 * @throws IllegalArgumentException if creator is not a producer, transformer or distributor
	 */
	public List<Product> filterByCreator(User creator) {
		ValidationUtils.checkCreator(creator);
		return productRepo.findAllProductsByOwner(creator);
	}

	/**
	 * Method to retrieve all products from the repository about the specified status
	 * @param status Status
	 * @return List of all products of the specified status
	 * @throws NullPointerException if status is null
	 */
	public List<Product> filterByStatus(Status status) {
		ValidationUtils.checkStatus(status);
		return productRepo.findAllProductsByStatus(status);
	}

	/**
	 * Method to retrieve all products from a specified creator and a specified status
	 * @param creator User
	 * @param status Status
	 * @return List of all products of the specified creator and the specified status
	 * @throws IllegalArgumentException if the creator or status parameters are invalid
	 */
	public List<Product> filterByCreatorAndStatus(User creator, Status status) {
		ValidationUtils.checkCreator(creator);
		ValidationUtils.checkStatus(status);
		return productRepo.findAllProductsByOwnerAndStatus(creator, status);
	}

	/**
	 * Method that approve a pending product
	 * @param p Product
	 * @param user User
	 * @throws IllegalArgumentException if user is null
	 * @throws IllegalArgumentException if product is null, had a null id or is not in pending state
	 */
	public void approve(Product p, User user) {
		ValidationUtils.checkUser(user);
		ValidationUtils.checkProductToApproveOrReject(p);
		productRepo.findById(p.getId()).ifPresent(product -> {
			product.setStatus(Status.APPROVED);
			productRepo.save(product);
		});
	}

	/**
	 * Method that reject a pending product
	 * @param p Product
	 * @param reason String
	 * @param user User
	 * @throws NullPointerException if user is null
	 * @throws IllegalArgumentException if creator is not a producer, transformer or distributor
	 * @throws IllegalArgumentException if product is null, had a null id or is not in pending state
	 */
	public void reject(Product p, String reason, User user) {
		ValidationUtils.checkCreator(user);
		ValidationUtils.checkProductToApproveOrReject(p);
		productRepo.findById(p.getId()).ifPresent(product -> {
			product.setStatus(Status.REJECTED);
			productRepo.delete(product);
		});
		System.out.println("Reason of the rejection :" + reason);
	}

	/**
	 * Method that add content to a product
	 * @param c Content
	 * @param productId Long
	 * @throws NullPointerException if content is null
	 */
	public void addContentToProduct(Content c, Long productId) {
		//TODO
		ValidationUtils.checkContent(c);
		productRepo.findById(productId).ifPresent(product -> {
			product.getSupplyChain().stream().forEach(phase -> phase.getApprovedContent().add(c)); //setContent(c);
            productRepo.save(product);
		});
	}

	/**
	 *Method to reject a content
	 * @param c Content
	 * @return c Content
	 */
	public Content rejectContent(Content c) {
		//TODO
		System.out.println("Content rejected...");
		return c;
	}
}