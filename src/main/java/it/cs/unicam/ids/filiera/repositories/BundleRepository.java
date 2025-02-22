package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.data.repository.CrudRepository;
import it.cs.unicam.ids.filiera.domainModel.products.*;

import java.util.List;

public interface BundleRepository extends CrudRepository<Bundle, Long>{
    List<Bundle> findAllBundlesByOwner(User owner);
    List<Bundle> findAllBundlesByStatus(Status status);
    List<Bundle> findAllBundlesByOwnerAndStatus(User creator, Status status);

}
