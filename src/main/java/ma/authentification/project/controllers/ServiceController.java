package ma.authentification.project.controllers;


import ma.authentification.project.entities.Service;
import ma.authentification.project.exceptions.ServiceException;
import ma.authentification.project.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Service>> findAllServices(){
        return new ResponseEntity<>(serviceService.findAllServices(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Service> findServiceById(@PathVariable Integer id) throws ServiceException {
        return new ResponseEntity<>(serviceService.findServiceById(id), HttpStatus.OK);
    }


    @GetMapping("/find/{price}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Service>> findServiceById(@PathVariable Double price) throws ServiceException {
        return new ResponseEntity<>(serviceService.findServicesByPrice(price), HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Service> findServiceById(@PathVariable String name) throws ServiceException {
        return new ResponseEntity<>(serviceService.findServiceByName(name), HttpStatus.OK);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Service> saveService(@RequestBody Service service) throws ServiceException {
        return new ResponseEntity<>(serviceService.saveService(service), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Service> updateService(@RequestBody Service service) throws ServiceException {
        return new ResponseEntity<>(serviceService.updateService(service), HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Service> deleteServiceById(@PathVariable Integer id) throws ServiceException {
        serviceService.deleteServiceById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
