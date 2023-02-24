package ma.authentification.project.controllers;

import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Client;
import ma.authentification.project.exceptions.ClientException;
import ma.authentification.project.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/client")
@AllArgsConstructor
@CrossOrigin
public class ClientController {

    private ClientService clientService;

     @GetMapping("/all")
     @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> findAllClients(){
         return new ResponseEntity<>(clientService.findAllClients(), HttpStatus.OK);
     }
    @GetMapping("id/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Client> findClientById(@PathVariable Integer id) throws ClientException {
         return new ResponseEntity<>(clientService.findClientById(id),HttpStatus.OK);
    }
    @GetMapping("cin/{cin}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Client> findClientsByCin(@PathVariable String cin) throws ClientException{
        return new ResponseEntity<>(clientService.findClientByCin(cin), HttpStatus.OK);
    }
    @GetMapping("lastname/{lastname}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Client>> findClientsByLastName(@PathVariable String lastname) throws ClientException{
         return new ResponseEntity<>(clientService.findClientsByLastName(lastname), HttpStatus.OK);

     }
    @PostMapping(value="/save",consumes = "application/json",produces = "application/json")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Client> saveClient(@RequestBody Client client) throws ClientException{
        return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
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
