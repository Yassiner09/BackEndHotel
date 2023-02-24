package ma.authentification.project.controllers;
import lombok.RequiredArgsConstructor;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.User;
import ma.authentification.project.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

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
