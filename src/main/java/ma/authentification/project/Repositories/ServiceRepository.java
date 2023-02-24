package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Integer> {

    List<Service> findAllByPrice(Double price);
    Optional<Service> findByName(String name);
}
