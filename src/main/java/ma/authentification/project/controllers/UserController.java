package ma.authentification.project.controllers;

import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.User;
import ma.authentification.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

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

    @GetMapping("{id}/user")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> findUserById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(userService.findUserById(id), OK);
    }

    @GetMapping("{id}/reservations")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<List<Reservation>> findUserReservations(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(userService.findUserById(id).getReservations(),OK);
    }






}
