package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.CatalogItem;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Product;

import it.cs.unicam.ids.filiera.repositories.ProductRepository;

import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;

	/**
	 * method to create a product in the repository
	 * @param name String
	 * @param owner AuthUser
	 * @param quantity Int
	 * @param price Double
	 * @param expiryDate Date
	 * @param category String
	 */
	public Product createProduct(String name, AuthUser owner, int quantity, Double price, Date expiryDate, String category) {
		ValidationUtils.checkCreator(owner);
		//TODO ALTRI CHECK
		Product product = new Product(name, price, owner, expiryDate, Status.PENDING, quantity, category);
		return productRepo.save(product);

		/*
		ValidationUtils.checkCreator(AuthUser);
		Scanner scanner = new Scanner(System.in)
		System.out.println("Creazione Prodotto");
		System.out.println("Inserisci il nome del prodotto: ");
		String name = scanner.nextLine();
		System.out.println("Inserisci la quantità del prodotto: ");
		int quantity = scanner.nextInt();
		System.out.println("Inserisci il prezzo del prodotto: ");
		double price = scanner.nextDouble();
		System.out.println("Inserisci la categoria del prodotto: ");
		String category = scanner.next();
		System.out.println("Inserisci la data di scadenza del prodotto (gg/mm/aaaa): ");
		String expiryDate = scanner.next();
		scanner.close();
		Product product = new Product(name, price, AuthUser, LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")), category, Status.PENDING, quantity);
		return productRepo.save(product);
		 */
	}

	/**
	 * Method to remove a product from the repository
	 * @param product Product
	 */
	public void deleteProduct(Product product){
		productRepo.delete(product);
	}

	/**
	 * Method to retrieve all products from the repository about the specified owner
	 * @param creator AuthUser
	 * @return List of all products of the specified owner
	 * @throws NullPointerException if creator is null
	 * @throws IllegalArgumentException if creator is not a producer, transformer or distributor
	 */
	public List<Product> filterByCreator(AuthUser creator) {
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
	 * @param creator AuthUser
	 * @param status Status
	 * @return List of all products of the specified creator and the specified status
	 * @throws IllegalArgumentException if the creator or status parameters are invalid
	 */
	public List<Product> filterByCreatorAndStatus(AuthUser creator, Status status) {
		ValidationUtils.checkCreator(creator);
		ValidationUtils.checkStatus(status);
		return productRepo.findAllProductsByOwnerAndStatus(creator, status);
	}

	/**
	 * Method that approve a pending product
	 * @param p Product
	 * @param AuthUser AuthUser
	 * @throws IllegalArgumentException if AuthAuthUser is null
	 * @throws IllegalArgumentException if product is null, had a null id or is not in pending state
	 */
	public void approve(Product p, AuthUser AuthUser) {
		ValidationUtils.checkCurator(AuthUser);
		ValidationUtils.checkPending(p);
		productRepo.findById(p.getId()).ifPresent(product -> {
			product.setStatus(Status.APPROVED);
			productRepo.save(product);
		});
		System.out.println("Product: " + p.getName() + " approved successfully ");
	}

	/**
	 * Method that reject a pending product
	 * @param p Product
	 * @param reason String
	 * @param AuthUser AuthUser
	 * @throws NullPointerException if AuthUser is null
	 * @throws IllegalArgumentException if creator is not a producer, transformer or distributor
	 * @throws IllegalArgumentException if product is null, had a null id or is not in pending state
	 */
	public void reject(Product p, String reason, AuthUser AuthUser) {
		ValidationUtils.checkCurator(AuthUser);
		ValidationUtils.checkPending(p);
		productRepo.findById(p.getId()).ifPresent(product -> {
			product.setStatus(Status.REJECTED);
			productRepo.delete(product);
		});
		System.out.println("Product: " + p.getName() + " rejected successfully" + "\nReason of the rejection :" + reason);
	}

	/**
	 * Method that add content to a product
	 * @param c Content
	 * @param productId Long
	 * @throws NullPointerException if content is null
	 */
	public void addContentToProduct(Content c, Long productId) {
		ValidationUtils.checkContent(c);
		productRepo.findById(productId).ifPresent(product -> {
			product.getSupplyChain().forEach(phase -> {
				phase.addContent(c);
			});
		});
	}

	/**
	 *Method to reject a content
	 * @param c Content
	 * @return c Content
	 */
	public Content rejectContent(Content c) {
		productRepo.findAll().forEach(product -> {
			product.getSupplyChain().forEach(phase -> {
				phase.getPendingContent().remove(c);
			});
		});
		System.out.println("Content rejected...");
		return c;
	}

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
	public void saveProduct(Product product){
		productRepo.save(product);
	}

	/**
	 * Method to retrieve the info about a product
	 * @param catalogItem C extends CatalogItem
	 * @return String with the info about the product
	 */
	public  <C extends CatalogItem> String toStringProductInfo(C catalogItem){
		return catalogItem.getInfo();
	}

	/*
	public <C extends CatalogItem> void save(C catalogItem){
		productRepo.save(catalogItem);
	}*/

	/*
	public <C extends CatalogItem> C remove(C catalogItem){
		if(catalogItem instanceof Product){
			productRepo.delete((Product) catalogItem);
		}
		else{
            throw new IllegalArgumentException("The object is not a Product");
        }
		return catalogItem;
	}*/









}