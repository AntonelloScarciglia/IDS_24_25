package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.products.Bundle;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BundleRepository extends CrudRepository<Bundle, Long> {
    List<Bundle> findAllBundlesByOwner(AuthUser creator);
    List<Bundle> findAllBundlesByStatus(Status status);
    List<Bundle> findAllBundlesByOwnerAndStatus(AuthUser creator, Status status);
    List<Bundle> findAllBundlesContainingProduct(Product product);
    Bundle getBundle(Bundle bundle);
}
