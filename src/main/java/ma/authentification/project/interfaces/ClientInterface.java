package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Client;
import ma.authentification.project.exceptions.ClientException;

import java.util.List;

 public interface ClientInterface {
     List<Client> findAllClients();

     Client findClientById(Integer id) throws ClientException;

     Client findClientByCin(String cin) throws ClientException;

     List<Client> findClientsByLastName(String lastname) throws ClientException;

     Client saveClient(Client client) throws ClientException;

     Client updateClient(Client client) throws ClientException;

     void deleteClientById(Integer id) throws ClientException;


}
