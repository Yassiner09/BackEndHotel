package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findByCin(String cin);

    List<Client> findAllByLastName(String lastname);


}
