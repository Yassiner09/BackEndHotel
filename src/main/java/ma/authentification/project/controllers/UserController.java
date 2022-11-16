package ma.authentification.project.controllers;

import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.Role;
import ma.authentification.project.entities.User;
import ma.authentification.project.services.RoleService;
import ma.authentification.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private UserService userService;
    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    @PostMapping({"/registerUser"})
    @PreAuthorize("hasRole('Admin')")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @DeleteMapping({"/{id}/deleteUser"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('Admin')")
    @Cacheable
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.findAll(), OK);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    @GetMapping("{idRole}/roles")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<User>> findUserByRoles(@PathVariable Integer idRole) throws Exception {
        Role role = roleService.findByIdRole(idRole);
        return new ResponseEntity<>(userService.findByRoles(role), OK);
    }

    @GetMapping("{id}/user")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(userService.findUserById(id), OK);
    }

    @GetMapping("findByUsername/{username}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) throws Exception {
        return new ResponseEntity<>(userService.findUserByUsername(username), OK);
    }

    @GetMapping("{id}/reservations")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<List<Reservation>> findUserReservations(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(userService.findUserById(id).getReservations(),OK);
    }

    @PostMapping("{username}/changePassword/{password}/{newPassword}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> changePassword(@PathVariable String username,@PathVariable String password, @PathVariable String newPassword) throws Exception {
        userService.changePassword(username,password,newPassword);
        return new ResponseEntity<>(OK);
    }






}
