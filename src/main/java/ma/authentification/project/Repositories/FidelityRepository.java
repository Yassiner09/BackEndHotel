package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Fidelity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FidelityRepository extends JpaRepository<Fidelity, Integer> {

    Optional<Fidelity> findByName(String name);
}
