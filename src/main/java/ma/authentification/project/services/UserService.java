package ma.authentification.project.services;

import lombok.RequiredArgsConstructor;
import ma.authentification.project.Repositories.RoleRepository;
import ma.authentification.project.Repositories.UserRepository;
import ma.authentification.project.entities.Role;
import ma.authentification.project.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
   
    private final UserRepository userRepository;
    
    private final RoleRepository roleRepository;
    
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public List<User> findByRoles(Role role){
        Role role1=roleRepository.findByIdRole(role.getIdRole());
        return userRepository.findByRoles(role1);
    }

    public User findUserById(Integer id) throws Exception {
        return userRepository.findById(id).orElseThrow(()->new Exception("user not found"));
    }

    public User findUserByUsername(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(()->new Exception("user not found"));
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
    public Boolean matchPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public void changePassword(String username, String password, String newPassword) throws Exception {
        User user = findUserByUsername(username);
        if (matchPassword(password, user.getPassword())) {
            user.setPassword(getEncodedPassword(newPassword));
            userRepository.save(user);
        } else {
            throw new Exception("password is incorrect");
        }
    }
}
