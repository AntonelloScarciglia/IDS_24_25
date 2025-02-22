package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.*;
import it.cs.unicam.ids.filiera.repositories.BundleRepository;
import it.cs.unicam.ids.filiera.util.Status;
import it.cs.unicam.ids.filiera.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundleService {
	@Autowired
	private BundleRepository bundleRepo;

	public Bundle createBundle(String name, User owner, List<Product> products) {
		ValidationUtils.checkCreator(owner);
		//ValidationUtils.checkProduct(products);
		Bundle bundle = new Bundle(name, 0.0, owner, null, products, 1, Status.PENDING);
		return bundleRepo.save(bundle);
	}

	/**
	 * Method to approve a pending bundle
	 * @param b bundle
	 * @param user user
	 * @throws IllegalArgumentException if bundle is null, had a null id or is not in pending state or user is not a curator
	 */
	public Bundle approve(Bundle b, User user) {
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
	 * Method to reject a pending bundle
	 * @param b bundle
	 * @param reason string
	 * @param user user
	 * @throws IllegalArgumentException if bundle is null, had a null id or is not in pending state or user is not a curator
	 */
	public Bundle reject(Bundle b, String reason, User user) {
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
	 * Method to get all bundles by the specified owner
	 * @param creator
	 * @return List<Bundle>
	 * @throws IllegalArgumentException if user is not a producer, transformer or a distributor
	 * @throws NullPointerException if user is null
	 */
	public List<Bundle> filterByCreator(User creator) {
		ValidationUtils.checkCreator(creator);
		return bundleRepo.findAllBundlesByOwner(creator);
	}

	/**
	 * Method to get all bundles by the specified status
	 * @param status
	 * @return List<Bundle>
	 * @throws NullPointerException if status is null
	 */
	public List<Bundle> filterByStatus(Status status) {
		ValidationUtils.checkStatus(status);
		return bundleRepo.findAllBundlesByStatus(status);
	}

	/**
	 * Method to get all bundles by the specified owner and status
	 * @param creator
	 * @param status
	 * @return List<Bundle>
	 * @throws NullPointerException if user or status is null
	 * @throws IllegalArgumentException if user is not a producer, transformer or a distributor
	 */
	public List<Bundle> filterByCreatorAndStatus(User creator, Status status) {
		ValidationUtils.checkCreator(creator);
		ValidationUtils.checkStatus(status);
		return bundleRepo.findAllBundlesByOwnerAndStatus(creator, status);
	}
}