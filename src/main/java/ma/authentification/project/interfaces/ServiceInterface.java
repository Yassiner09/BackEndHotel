package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Service;
import ma.authentification.project.exceptions.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

public interface ServiceInterface {

    public List<Service> findAllServices()throws ServiceException;

    public Service findServiceById(Integer id)throws ServiceException;

    public Service findServiceByName(String name)throws ServiceException;

    public List<Service> findServicesByPrice(Double price)throws ServiceException;

    public Service saveService(Service service)throws ServiceException;

    public Service updateService(Service service)throws ServiceException;

    public void deleteServiceById(Integer id)throws ServiceException;
}
