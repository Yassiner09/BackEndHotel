package ma.authentification.project.services;

import ma.authentification.project.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


}
