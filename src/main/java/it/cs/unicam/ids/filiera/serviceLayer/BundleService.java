package it.cs.unicam.ids.filiera.serviceLayer;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.*;
import it.cs.unicam.ids.filiera.repositories.BundleRepository;

import java.util.List;

public class BundleService {

	private BundleRepository bundleRepo;

	public Bundle createBundle() {
		// TODO - implement BundleService.createBundle
		throw new UnsupportedOperationException();

	}

	/**
	 * 
	 * @param b
	 */
	public Bundle approve(Bundle b) {
		// TODO - implement BundleService.approve
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param b
	 * @param reason
	 */
	public Bundle reject(Bundle b, String reason) {
		// TODO - implement BundleService.reject
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param creator
	 */
	public List<Bundle> filterByCreator(User creator) {
		// TODO - implement BundleService.filterByCreator
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param status
	 */
	public List<Bundle> filterByStatus(boolean status) {
		// TODO - implement BundleService.filterByStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param creator
	 * @param status
	 */
	public List<Bundle> filterByCreatorAndStatus(User creator, boolean status) {
		// TODO - implement BundleService.filterByCreatorAndStatus
		throw new UnsupportedOperationException();
	}
}