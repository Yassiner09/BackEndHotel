package ma.authentification.project.controllers;

import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Fidelity;
import ma.authentification.project.exceptions.FidelityException;
import ma.authentification.project.services.FidelityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fidelity")
@AllArgsConstructor
@CrossOrigin
public class FidelityController {
    private FidelityService fidelityService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Fidelity>> findAllFidelities() {
        return new ResponseEntity<>(fidelityService.getAllFidelities(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Fidelity> findFidelityById(@PathVariable Integer id) throws FidelityException {
        return new ResponseEntity<>(fidelityService.findFidelityById(id), HttpStatus.OK);
    }
    @GetMapping("/findName/{name}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Fidelity> findFidelityByName(@PathVariable String name) throws FidelityException {
        return new ResponseEntity<>(fidelityService.findFidelityByName(name), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Fidelity> addFidelity(@RequestBody Fidelity fidelity){
        return new ResponseEntity<>(fidelityService.addFidelity(fidelity), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    @PreAuthorize(( "hasRole('Admin')"))
    public ResponseEntity<Fidelity> updateFidelity(@RequestBody Fidelity fidelity){
        return new ResponseEntity<>(fidelityService.updateFidelity(fidelity), HttpStatus.OK);
    }
}
