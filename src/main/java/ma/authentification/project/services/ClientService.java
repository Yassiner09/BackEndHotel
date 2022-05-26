package ma.authentification.project.services;

import ma.authentification.project.Repositories.ClientRepository;
import ma.authentification.project.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    private List<Client> findAll(){
        return clientRepository.findAll();
    }
    private Client findClientById(Integer id) throws Exception{
        return clientRepository.findById(id).orElseThrow(()->new Exception("wtf"));
    }

    private void deleteClientByid(Integer id){
        clientRepository.deleteById(id);
    }


}
