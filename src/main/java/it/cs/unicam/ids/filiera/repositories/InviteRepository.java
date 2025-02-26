package it.cs.unicam.ids.filiera.repositories;

import it.cs.unicam.ids.filiera.domainModel.products.Invite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InviteRepository extends CrudRepository<Invite, Long>{

}
