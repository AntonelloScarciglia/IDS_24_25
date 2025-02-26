package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Bundle;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.serviceLayer.BundleService;
import it.cs.unicam.ids.filiera.serviceLayer.ProductService;
import it.cs.unicam.ids.filiera.serviceLayer.UserService;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bundle")
public class BundleController {
	@Autowired
	private BundleService bundleService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	/**
	 * Retrieves a bundle by its ID.
	 *
	 * @param bundleId The ID of the bundle.
	 * @return A ResponseEntity containing the bundle or a 404 status if not found.
	 */
	@GetMapping("/{bundleId}")
	public ResponseEntity<Bundle> getBundle(@PathVariable Long bundleId) {
		Bundle bundle = bundleService.getBundleById(bundleId);
		if (bundle == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bundle);
	}

	/**
	 * Retrieves all pending bundles.
	 *
	 * @return A ResponseEntity containing a list of pending bundles.
	 */
	@GetMapping("/pending")
	public ResponseEntity<List<Bundle>> getAllPendingBundles() {
		List<Bundle> bundles = bundleService.filterByStatus(Status.PENDING);
		return ResponseEntity.ok(bundles);
	}

	/**
	 * Retrieves all approved bundles.
	 *
	 * @return A ResponseEntity containing a list of approved bundles.
	 */
	@GetMapping("/approved")
	public ResponseEntity<List<Bundle>> getAllApprovedBundles() {
		List<Bundle> bundles = bundleService.filterByStatus(Status.APPROVED);
		return ResponseEntity.ok(bundles);
	}

	/**
	 * Retrieves all approved bundles created by a specific creator.
	 *
	 * @param userId The ID of the creator.
	 * @return A ResponseEntity containing a list of approved bundles by the creator.
	 */
	@GetMapping("/approved/byCreator")
	public ResponseEntity<List<Bundle>> getAllApprovedBundleByCreator(@RequestParam Long userId) {

		AuthUser creator = userService.getUserById(userId);
		List<Bundle> bundles = bundleService.filterByCreatorAndStatus(creator, Status.APPROVED);
		return ResponseEntity.ok(bundles);
	}

	/**
	 * Approves a bundle.
	 *
	 * @param bundleId The ID of the bundle to approve.
	 * @param userId   The ID of the curator performing the approval.
	 * @return A ResponseEntity with a success message.
	 */
	@PostMapping("/approve")
	public ResponseEntity<String> approveBundle(@RequestParam Long bundleId, @RequestParam Long userId) {
		AuthUser curator = userService.getUserById(userId);
		Bundle bundle = bundleService.getBundleById(bundleId);

		if (bundle == null) {
			return ResponseEntity.notFound().build();
		}
		bundleService.approved(bundle, curator);
		return ResponseEntity.ok("Bundle approved successfully.");
	}

	/**
	 * Rejects a bundle.
	 *
	 * @param bundleId The ID of the bundle to reject.
	 * @param reason   The reason for rejection.
	 * @param userId   The ID of the curator performing the rejection.
	 * @return A ResponseEntity with a success message.
	 */
	@PostMapping("/reject")
	public ResponseEntity<String> rejectBundle(@RequestParam Long bundleId, @RequestParam String reason, @RequestParam Long userId) {
		AuthUser curator = userService.getUserById(userId);
		Bundle bundle = bundleService.getBundleById(bundleId);

		if (bundle == null) {
			return ResponseEntity.notFound().build();
		}

		bundleService.rejected(bundle, reason, curator);
		return ResponseEntity.ok("Bundle rejected successfully.");
	}

	@PostMapping("/create")
	public ResponseEntity<Bundle> createBundle() {
		// TODO - implement BundleController.createBundle
		throw new UnsupportedOperationException();
	}

	/**
	 * Adds a product to an existing bundle.
	 *
	 * @param bundleId  The ID of the bundle.
	 * @param productId The ID of the product to add.
	 * @return A ResponseEntity with a success message.
	 */
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProductToBundle(@RequestParam Long bundleId, @RequestParam Long productId) {

		Product product = productService.getProduct(productId);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		bundleService.addProductToBundle(bundleId, product);
		return ResponseEntity.ok("Product added to bundle successfully.");
	}

	public void initBundle() {
		// TODO - implement BundleController.initBundle
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param product
	 */
	@DeleteMapping("/removeProduct")
	public  ResponseEntity<String> removeProduct(int product) {
		// TODO - implement BundleController.removeProduct
		throw new UnsupportedOperationException();
	}
}
