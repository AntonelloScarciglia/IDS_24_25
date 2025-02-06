package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.repositories.ProductRepository;

import java.util.List;

public class ProductService {

	private ProductRepository productRepo;

	public Product createProduct() {
		// TODO - implement ProductService.createProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param creator
	 */
	public List<Product> filterByCreator(User creator) {
		// TODO - implement ProductService.filterByCreator
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param status
	 */
	public List<Product> filterByStatus(boolean status) {
		// TODO - implement ProductService.filterByStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param creator
	 * @param status
	 */
	public List<Product> filterByCreatorAndStatus(User creator, boolean status) {
		// TODO - implement ProductService.filterByCreatorAndStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public Product approve(Product p) {
		// TODO - implement ProductService.approve
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 * @param reason
	 */
	public Product reject(Product p, String reason) {
		// TODO - implement ProductService.reject
		throw new UnsupportedOperationException();
	}


	public Product addContentToProduct(Content c, Long productId) {
		// TODO - implement ProductService.addContentToProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	public Content rejectContent(Content c) {
		// TODO - implement ProductService.rejectContent
		throw new UnsupportedOperationException();
	}

}