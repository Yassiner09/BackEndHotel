package ma.authentification.project.services;

import ma.authentification.project.Repositories.RoleRepository;
import ma.authentification.project.Repositories.UserRepository;
import ma.authentification.project.entities.Role;
import ma.authentification.project.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User addUser(User user){
        return userRepository.save(user);
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


    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
