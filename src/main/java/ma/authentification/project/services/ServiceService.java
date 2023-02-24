package ma.authentification.project.services;

import lombok.RequiredArgsConstructor;
import ma.authentification.project.Repositories.ServiceRepository;
import ma.authentification.project.exceptions.ServiceException;
import ma.authentification.project.interfaces.ServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceService implements ServiceInterface {

    private final ServiceRepository serviceRepository;

    @Override
    public List<ma.authentification.project.entities.Service> findAllServices(){
        return serviceRepository.findAll();
    }
    @Override
    public ma.authentification.project.entities.Service findServiceById(Integer id) throws ServiceException{
        return serviceRepository.findById(id).orElseThrow(()->new ServiceException("Service not found !"));
    }

    @Override
    public ma.authentification.project.entities.Service findServiceByName(String name) throws ServiceException {
        return serviceRepository.findByName(name).orElseThrow(()->new ServiceException("Service with "+name+" as a name not found !"));
    }

    @Override
    public List<ma.authentification.project.entities.Service> findServicesByPrice(Double price) {
        return serviceRepository.findAllByPrice(price);
    }

    @Override
    public ma.authentification.project.entities.Service saveService(ma.authentification.project.entities.Service service){
        return serviceRepository.save(service);
    }
     @Override
    public ma.authentification.project.entities.Service updateService(ma.authentification.project.entities.Service service){
        return serviceRepository.save(service);
    }

    @Override
    public void deleteServiceById(Integer id) {
        serviceRepository.deleteById(id);
    }


}
