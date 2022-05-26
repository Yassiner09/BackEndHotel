package ma.authentification.project.controllers;

import ma.authentification.project.entities.Client;
import ma.authentification.project.exceptions.ClientException;
import ma.authentification.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

     @GetMapping("/all")
     @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> findAllClients(){
         return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
     }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Client> findClientById(@PathVariable Integer id) throws ClientException {
         return new ResponseEntity<>(clientService.findClientById(id),HttpStatus.OK);
    }
    @GetMapping("/{cin}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> findClientsByCin(@PathVariable String cin)throws ClientException{
        return new ResponseEntity<>(clientService.findClientsByCin(cin), HttpStatus.OK);
    }
     @GetMapping("/{lastname}")
     @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> findClientsByLastName(@PathVariable String lastName)throws ClientException{
         return new ResponseEntity<>(clientService.findClientsByLastName(lastName), HttpStatus.OK);

     }
    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Client> saveClient(@RequestBody Client client)throws ClientException{
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED    );
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Client> updateClient(@RequestBody Client client)throws ClientException{
         return new ResponseEntity<>(clientService.updateClient(client), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteClientById(@PathVariable Integer id)throws ClientException{
         clientService.deleteClientById(id);
         return new ResponseEntity<>(HttpStatus.OK);
    }




}
