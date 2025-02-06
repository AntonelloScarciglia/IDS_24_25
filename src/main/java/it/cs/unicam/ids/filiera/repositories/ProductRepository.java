package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.products.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
