package ma.authentification.project.services;

import lombok.RequiredArgsConstructor;
import ma.authentification.project.Repositories.ClientRepository;
import ma.authentification.project.entities.Client;
import ma.authentification.project.exceptions.ClientException;
import ma.authentification.project.interfaces.ClientInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClientService implements ClientInterface {

    //@Autowired
    private final ClientRepository clientRepository;

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }
    public Client findClientById(Integer id) throws ClientException {
        return clientRepository.findById(id).orElseThrow(()->new ClientException("Client with "+id+"not found !"));
    }

    @Override
    public Client findClientByCin(String cin)throws ClientException {
        return clientRepository.findByCin(cin).orElseThrow(()->new ClientException("Client with "+cin+"not found !"));
    }

    @Override
    public List<Client> findClientsByLastName(String lastname)throws ClientException {
        List<Client> clients = clientRepository.findAllByLastName(lastname);
        if(clients.isEmpty())
            throw new ClientException("Client with lastname: "+lastname+" not found !");
        return clients;
    }

    @Override
    public Client saveClient(Client client){
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClientById(Integer id) throws ClientException{
        Client client=clientRepository.findById(id).orElseThrow(()->new ClientException("Client with "+id+"not found !"));
        clientRepository.delete(client);
    }




}
