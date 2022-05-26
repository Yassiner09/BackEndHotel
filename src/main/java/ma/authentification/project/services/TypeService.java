package ma.authentification.project.services;

import ma.authentification.project.Repositories.TypeRepository;
import ma.authentification.project.entities.Type;
import ma.authentification.project.exceptions.TypeException;
import ma.authentification.project.interfaces.TypeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeService implements TypeInterface {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> findAllTypes() throws TypeException {
        return typeRepository.findAll();
    }

    @Override
    public Type findTypeById(Integer id) throws TypeException {
        return typeRepository.findById(id).orElseThrow(()->new TypeException("Type with "+id+" as an id not found !"));
    }

    @Override
    public Type findTypeByName(String name) throws TypeException {
        return typeRepository.findByName(name).orElseThrow(()->new TypeException("Type with "+name+" as a name not found !"));
    }

    @Override
    public Type saveType(Type type) throws TypeException {
        return typeRepository.save(type);
    }

    @Override
    public Type updateType(Type type) throws TypeException {
        return typeRepository.save(type);
    }

    @Override
    public void deleteTypeById(Integer id) throws TypeException {
        typeRepository.deleteById(id);
    }
}
