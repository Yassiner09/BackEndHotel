package ma.authentification.project.services;

import ma.authentification.project.Repositories.ClientRepository;
import ma.authentification.project.entities.Client;
import ma.authentification.project.exceptions.ClientException;
import ma.authentification.project.interfaces.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService implements ClientInterface {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }
    public Client findClientById(Integer id) throws ClientException {
        return clientRepository.findById(id).orElseThrow(()->new ClientException("Client with "+id+"not found !"));
    }

    @Override
    public List<Client> findClientsByCin(String cin)throws ClientException {
        return clientRepository.findAllByCin(cin);
    }

    @Override
    public List<Client> findClientsByLastName(String lastname)throws ClientException {
        return clientRepository.findAllByLastName(lastname);
    }

    @Override
    public Client saveClient(Client client)throws ClientException {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client)throws ClientException {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClientById(Integer id) throws ClientException{
        Client client=clientRepository.findById(id).get();
        clientRepository.delete(client);
    }



}
