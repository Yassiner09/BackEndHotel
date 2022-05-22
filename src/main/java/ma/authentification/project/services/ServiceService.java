package ma.authentification.project.services;

import ma.authentification.project.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;


}
