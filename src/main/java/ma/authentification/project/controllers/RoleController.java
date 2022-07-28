package ma.authentification.project.controllers;

import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Role;
import ma.authentification.project.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("api/role")
@AllArgsConstructor
@CrossOrigin
public class RoleController {
    private RoleService roleService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Collection<Role>> findAllRoles() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }



    @PutMapping("/update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Role> updateType(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.updateRole(role), HttpStatus.OK);
    }
    @PostMapping({"/add"})
    @PreAuthorize("hasRole('Admin')")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }


    @DeleteMapping({"/delete/{id}"})
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> createNewRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
