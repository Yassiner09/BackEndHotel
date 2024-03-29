package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String user);
    Role findByIdRole(Integer id);
}
