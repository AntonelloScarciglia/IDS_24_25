package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.products.Bundle;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.serviceLayer.BundleService;
import it.cs.unicam.ids.filiera.serviceLayer.ProductService;

import java.util.List;

public class CuratorController {
	ProductService productService = new ProductService();
	BundleService bundleService = new BundleService();

	public List<Product> getAllPendingProducts() {
		// TODO - implement CuratorController.getAllPendingProducts
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productId
	 */
	public Product approveProduct(Long productId) {
		// TODO - implement CuratorController.approveProduct
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param productId
	 */
	public Product rejectProduct(Long productId) {
		// TODO - implement CuratorController.rejectProduct
		throw new UnsupportedOperationException();
	}

	public Content approveContent() {
		// TODO - implement CuratorController.approveContent
		throw new UnsupportedOperationException();
	}

	public Content rejectContent() {
		// TODO - implement CuratorController.rejectContent
		throw new UnsupportedOperationException();
	}

	public List<Bundle> getPendingBundles() {
		// TODO - implement CuratorController.getPendingBundles
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bundleId
	 */
	public Bundle approveBundle(Long bundleId) {
		// TODO - implement CuratorController.approveBundle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param bundleId
	 */
	public Bundle rejectBundle(Long bundleId) {
		// TODO - implement CuratorController.rejectBundle
		throw new UnsupportedOperationException();
	}

}