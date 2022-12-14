package ma.authentification.project.services;

import ma.authentification.project.Repositories.RoleRepository;
import ma.authentification.project.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;


@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

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
