package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Bundle;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.repositories.BundleRepository;
import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;


import java.util.List;

public class BundleService {

	private BundleRepository bundleRepo;

	/**
	 * Creates a new bundle with the specified name, owner, and list of products. The bundle is
	 * initialized with a default status of PENDING and a quantity of 1. The owner is validated before
	 * creation, and the newly created bundle is saved to the repository.
	 *
	 * @param name     the name of the bundle to be created
	 * @param owner    the AuthUser who is the creator/owner of the bundle
	 * @param products the list of products to be included in the bundle
	 * @return the newly created and saved bundle
	 */
	public Bundle createBundle(String name, AuthUser owner, List<Product> products) {
		ValidationUtils.checkCreator(owner);

		for (Product product : products) {
			ValidationUtils.checkProduct(product);
		}

		double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();

		Bundle bundle = new Bundle(name, totalPrice, owner, null, products, 1, Status.PENDING);
		return bundleRepo.save(bundle);
	}

	/**
	 * Approves the given bundle by changing its status to APPROVED and saving the updated bundle.
	 * The user performing this operation must have a "Curator" role, and the bundle must be in a PENDING state.
	 *
	 * @param b the bundle to be approved, which must be in the PENDING state
	 * @param user the AuthUser performing the approval, who must have a Curator role
	 * @return the updated bundle with its status set to APPROVED
	 * @throws IllegalArgumentException if the bundle is not in a PENDING state or if the user is not a curator
	 * @throws NullPointerException if any of the required parameters are null
	 */
	public Bundle approved(Bundle b, AuthUser user) {
		ValidationUtils.checkCurator(user);
		ValidationUtils.checkPending(b);
		bundleRepo.findById(b.getId()).ifPresent(bundle -> {
			bundle.setStatus(Status.APPROVED);
			bundleRepo.save(b);
		});
		System.out.println("Bundle: " + b.getName() + " approved successfully ");
		return b;
	}

	/**
	 * Rejects the given bundle by changing its status to REJECTED and saving the updated bundle.
	 * The user performing this operation must have a "Curator" role, and the bundle must be in a PENDING state.
	 * A rejection reason is also logged as part of the operation.
	 *
	 * @param b the bundle to be rejected, which must be in the PENDING state
	 * @param reason the reason for rejecting the bundle
	 * @param user the AuthUser performing the rejection, who must have a Curator role
	 * @return the updated bundle with its status set to REJECTED
	 * @throws IllegalArgumentException if the bundle is not in PENDING state or if the user is not a curator
	 * @throws NullPointerException if any of the required parameters are null
	 */
	public Bundle rejected(Bundle b, String reason, AuthUser user) {
		ValidationUtils.checkCurator(user);
		ValidationUtils.checkPending(b);
		bundleRepo.findById(b.getId()).ifPresent(bundle -> {
			bundle.setStatus(Status.REJECTED);
			bundleRepo.save(b);
		});
		System.out.println("Bundle: " + b.getName() + " rejected successfully" + "\nReason of the rejection: " + reason);
		return b;
	}

	/**
	 * Filters and retrieves a list of bundles created by the specified creator.
	 *
	 * @param creator the AuthUser object representing the creator of the bundles
	 * @return a list of bundles created by the specified creator
	 */
	public List<Bundle> filterByCreator(AuthUser creator) {
		ValidationUtils.checkCreator(creator);
		return bundleRepo.findAllBundlesByOwner(creator);
	}

	/**
	 * Filters the list of bundles based on the provided status.
	 *
	 * @param status the status to filter the bundles by
	 * @return a list of bundles that match the specified status
	 */
	public List<Bundle> filterByStatus(Status status) {
		ValidationUtils.checkStatus(status);
		return bundleRepo.findAllBundlesByStatus(status);
	}

	/**
	 * Filters and retrieves a list of bundles created by a specific creator and matching a given status.
	 *
	 * @param creator the AuthUser who owns the bundles
	 * @param status the status of the bundles to filter
	 * @return a list of bundles that match the specified creator and status
	 */
	public List<Bundle> filterByCreatorAndStatus(AuthUser creator, Status status) {
		ValidationUtils.checkCreator(creator);
		ValidationUtils.checkStatus(status);
		return bundleRepo.findAllBundlesByOwnerAndStatus(creator, status);
	}

	/**
	 * Initializes a default bundle with predefined attributes and saves it to the repository.
	 * The default bundle is created with a name of "Default Bundle", a price of 0.0, no owner,
	 * no expiry date, an empty list of products, a quantity of 1, and a status of PENDING.
	 */
	public void initBundle() {
		Bundle defaultBundle = new Bundle("Default Bundle", 0.0, null, null, List.of(), 1, Status.PENDING);
		bundleRepo.save(defaultBundle);
		System.out.println("Default bundle initialized successfully.");
	}

	/**
	 * Adds a product to the specified bundle. The product is validated before being added,
	 * and the total price of the bundle is updated accordingly. Once modified, the updated
	 * bundle is saved to the repository.
	 *
	 * @param bundleId the unique identifier of the bundle to which the product will be added
	 * @param product the product to be added to the bundle; must be a valid product
	 * @throws IllegalArgumentException if the bundle with the given ID does not exist
	 */
	public void addProductToBundle(Long bundleId, Product product) {
		Bundle bundle = bundleRepo.findById(bundleId)
				.orElseThrow(() -> new IllegalArgumentException("Bundle not found with ID: " + bundleId));

		ValidationUtils.checkProduct(product);
		bundle.getProducts().add(product);
		double updatedPrice = bundle.getProducts().stream().mapToDouble(Product::getPrice).sum();
		bundle.setPrice(updatedPrice);

		bundleRepo.save(bundle);
		System.out.println("Product: " + product.getInfo() + " has been added to bundle with ID: " + bundleId);
	}

	/**
	 * Removes a product from all bundles that contain it. If the product is not found in a bundle, no action is taken for that bundle.
	 *
	 * @param product the product to be removed from all bundles
	 */
	public void removeProduct(Product product) {
		ValidationUtils.checkProduct(product);
		List<Bundle> bundlesContainingProduct = bundleRepo.findAllBundlesContainingProduct(product);

		if(bundlesContainingProduct.isEmpty()) {
			System.out.println("Product: " + product.getInfo() + " is not found in any bundle.");
			return;
		}

		for (Bundle bundle : bundlesContainingProduct) {
			bundle.getProducts().remove(product);
			double updatedPrice = bundle.getProducts().stream().mapToDouble(Product::getPrice).sum();
			bundle.setPrice(updatedPrice);
			bundleRepo.save(bundle);
			System.out.println("Product: " + product.getInfo() + " has been removed from bundle: " + bundle.getName());
		}
	}

	/**
	 * Updates the status of the specified bundle to the provided status.
	 *
	 * @param status the new status to be set for the bundle
	 * @param bundle the bundle whose status is to be updated
	 */
	public void setStatus(Status status, Bundle bundle) {
		bundleRepo.getBundle(bundle).setStatus(status);
	}
}