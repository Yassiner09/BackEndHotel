package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {

    Optional<Type> findByName(String name);
}
