package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.products.Phase;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.serviceLayer.BundleService;
import it.cs.unicam.ids.filiera.serviceLayer.ProductService;

import java.util.List;

public class SellerController {
	BundleService bundleService = new BundleService();
	ProductService productService = new ProductService();

	/**
	 * 
	 * @param sellerId
	 */
	public List<Product> getAllApprovedProducts(Long sellerId) {
		// User seller = userService.getUser(sellerId)
		// return productService.filterByCreator(seller)
		throw new UnsupportedOperationException();
	}

	public Product createProduct() {
		// TODO - implement SellerController.createProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productId
	 */
	public String getProductDetails(Long productId) {
		// TODO - implement SellerController.getProductDetails
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productId
	 * @param supplyChain
	 */
	public void addSupplyChainToProduct(int productId, List<Phase> supplyChain) {
		// TODO - implement SellerController.addSupplyChainToProduct
		throw new UnsupportedOperationException();
	}

	public Product addContentToProductPhase() {
		// TODO - implement SellerController.addContentToProductPhase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productId
	 */
	public void getProduct(Long productId) {
		// TODO - implement SellerController.getProduct
		throw new UnsupportedOperationException();
	}

	public void createBundle() {
		// TODO - implement SellerController.createBundle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bundleId
	 * @param productIds
	 */
	public void addProductsToBundle(Long bundleId, List<Long> productIds) {
		// TODO - implement SellerController.addProductsToBundle
		throw new UnsupportedOperationException();
	}

}