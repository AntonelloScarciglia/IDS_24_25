package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.Role;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Product;

import it.cs.unicam.ids.filiera.repositories.ProductRepository;

import it.cs.unicam.ids.filiera.util.Status;
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
	 * @param id
	 * @return Product
	 */
	public Product getProduct(Long id){
		return productRepo.findById(id).orElse(null);
	}

	/**
     * method to save a new product in the repository
     * @param product
     */
	public void saveProduct(Product product) {
		productRepo.save(product);
	}

	/**
	 * Method to retrieve all products from the repository about the specified owner
	 * @param creator
	 * @return List of all products of the specified owner
	 */
	public List<Product> filterByCreator(User creator) {
		checkUser(creator);
		return (List<Product>) productRepo.findAllProductsById(creator.getId());
	}

	/**
	 * Method to retrieve all products from the repository about the specified status
	 * @param status
	 * @return List of all products of the specified status
	 */
	public List<Product> filterByStatus(Status status) {
		checkStatus(status);
		return (List<Product>) productRepo.findAllProductsByStatus(status);
	}

	/**
	 * Method to retrieve all products from a specified creator and a specified status
	 * @param creator
	 * @param status
	 * @return List of all products of the specified creator and the specified status
	 */
	public List<Product> filterByCreatorAndStatus(User creator, Status status) {
		checkUser(creator);
		checkStatus(status);
		return (List<Product>) productRepo.findAllProductsByCreatorAndStatus(creator.getId(), status);
	}

	/**
	 * Method that approve a pending product
	 * @param p
	 */
	public void approve(Product p, User user) {
		checkUser(user);
		checkProductToApproveOrReject(p);
		productRepo.findById(p.getId()).ifPresent(product -> {
			product.setStatus(Status.APPROVED);
			productRepo.save(product);
		});
	}

	/**
	 * Method that reject a pending product
	 * @param p
	 * @param reason
	 */
	public void reject(Product p, String reason, User user) {
		checkUser(user);
		checkProductToApproveOrReject(p);
		productRepo.findById(p.getId()).ifPresent(product -> {
			product.setStatus(Status.REJECTED);
			productRepo.delete(product);
		});
		System.out.println("Reason of the rejection :" + reason);
	}

	/**
	 * Method that add content to a product
	 * @param c
	 * @param productId
	 */
	public void addContentToProduct(Content c, Long productId) {
		checkContent(c);
		checkProduct(productId);
		productRepo.findById(productId).ifPresent(product -> {
			product.setContent(c);
            productRepo.save(product);
		});
	}

//	/**
//	 *
//	 * @param c
//	 */
//	public Content rejectContent(Content c) {
//		// TODO - implement ProductService.rejectContent
//		throw new UnsupportedOperationException();
//	}

	private void checkCreator(User creator) {
        if(creator == null || creator.getId() == null){
			throw new NullPointerException("Creator must not be null");
		}
		if(creator.getRole().equals(Role.ADMIN) || creator.getRole().equals(Role.CURATOR) || creator.getRole().equals(Role.ANIMATOR)){
			throw new IllegalArgumentException("User must be a producer, a transformer or a distributor");
		}
    }

	private void checkStatus(Status status) {
        if(status == null){
			throw new NullPointerException("Status must not be null");
		}
    }

	private void checkProductToApproveOrReject(Product p) {
        if(p == null || p.getId() == null || p.getStatus()!= Status.PENDING){
            throw new IllegalArgumentException("Product must not be null, must have a non-null ID and status PENDING");
        }
		checkProduct(p.getId());
    }

	private void checkProduct(Long id) {
		if(productRepo.findById(id).isEmpty()) {
			throw new IllegalArgumentException("Product not found");
		}
	}

	private void checkContent(Content c) {
        if(c == null){
            throw new NullPointerException("Content must not be null");
        }
    }

	private void checkUser(User user){
		if(!(user.getRole().equals(Role.CURATOR))){
			throw new IllegalArgumentException("User must be a curator");
		}
	}
}