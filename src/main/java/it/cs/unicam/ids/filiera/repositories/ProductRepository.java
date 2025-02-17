package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.util.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllProductsById (String id);
    List<Product> findAllProductsByStatus (Status status);
    List<Product> findAllProductsByCreatorAndStatus(String id, Status status);
}
