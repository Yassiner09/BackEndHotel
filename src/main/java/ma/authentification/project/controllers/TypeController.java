package ma.authentification.project.controllers;


import ma.authentification.project.entities.Type;
import ma.authentification.project.exceptions.TypeException;
import ma.authentification.project.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Type>> findAllTypes()throws TypeException {
        return new ResponseEntity<>(typeService.findAllTypes(), HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Type> findTypeById(@PathVariable Integer id)throws TypeException {
        return new ResponseEntity<>(typeService.findTypeById(id), HttpStatus.OK);
    }


    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Type> findTypeByName(@PathVariable String name)throws TypeException {
        return new ResponseEntity<>(typeService.findTypeByName(name), HttpStatus.OK);
    }


    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Type> saveService(@RequestBody Type type)throws TypeException {
        return new ResponseEntity<>(typeService.saveType(type), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Type> updateService(@RequestBody Type type)throws TypeException {
        return new ResponseEntity<>(typeService.saveType(type), HttpStatus.OK);
    }

    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Type> deleteServiceById(@PathVariable Integer id)throws TypeException {
        typeService.deleteTypeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
