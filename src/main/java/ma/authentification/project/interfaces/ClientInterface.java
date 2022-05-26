package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Client;
import ma.authentification.project.exceptions.ClientException;
import org.apache.catalina.connector.ClientAbortException;

import java.util.List;

public interface ClientInterface {
    public List<Client> findAllClients() throws ClientException;

    public Client findClientById(Integer id) throws ClientException;

    public List<Client> findClientsByCin(String cin)throws ClientException;

    public List<Client> findClientsByLastName(String lastname)throws ClientException;

    public Client saveClient(Client client)throws ClientException;

    public Client updateClient(Client client)throws ClientException;

    public void deleteClientById(Integer id)throws ClientException;


}
