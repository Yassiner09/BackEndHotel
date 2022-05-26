package ma.authentification.project.services;

import ma.authentification.project.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public List<ma.authentification.project.entities.Service> findAllServices(){
        return serviceRepository.findAll();
    }
    public ma.authentification.project.entities.Service findServiceById(Integer id) throws Exception{
        return serviceRepository.findById(id).orElseThrow(()->new Exception("wtf"));
    }

    public ma.authentification.project.entities.Service addService(ma.authentification.project.entities.Service service){
        return serviceRepository.save(service);
    }
    public ma.authentification.project.entities.Service updateService(ma.authentification.project.entities.Service service){
        return serviceRepository.save(service);
    }
    public void deleteService(Integer id){
        serviceRepository.deleteById(id);
    }



}
