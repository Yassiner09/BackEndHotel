package ma.authentification.project.services;

import lombok.RequiredArgsConstructor;
import ma.authentification.project.Repositories.RoleRepository;
import ma.authentification.project.entities.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;


@Service
@Transactional
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Collection<Role> findAll(){
        return roleRepository.findAll();
    }
    public Role findByIdRole(Integer id){
        return roleRepository.findByIdRole(id);
    }
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
    public void deleteRole(Integer id){
        roleRepository.deleteById(id);
    }



}
