package ma.authentification.project.services;

import ma.authentification.project.Repositories.RoleRepository;
import ma.authentification.project.Repositories.UserRepository;
import ma.authentification.project.entities.Role;
import ma.authentification.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(Integer id) throws Exception {
        return userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
    }


    public User registerNewUser(User user) {
        Role role = roleRepository.findByName("User");
        user.addRoleToUser(role);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    //with json(angular)
    public User registerNewUserWithRole(User user) {
        Role role=null;
        if(user.getRoles().contains(roleRepository.findByName("Admin")))
            role = roleRepository.findByName("Admin");
        else if (user.getRoles().contains(roleRepository.findByName("User"))) {
            role=roleRepository.findByName("user");
        }
        user.addRoleToUser(role);
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }



    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
