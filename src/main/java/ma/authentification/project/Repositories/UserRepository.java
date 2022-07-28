package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Role;
import ma.authentification.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    List<User> findByRoles(Role role);

}
