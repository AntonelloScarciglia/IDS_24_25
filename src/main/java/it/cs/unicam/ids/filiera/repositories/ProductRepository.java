package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllProductsByOwner(User creator);
    List<Product> findAllProductsByStatus(Status status);
    List<Product> findAllProductsByOwnerAndStatus(User creator, Status status);
}
