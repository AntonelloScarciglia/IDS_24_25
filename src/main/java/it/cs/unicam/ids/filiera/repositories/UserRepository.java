package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AuthUser, Long> {
}
