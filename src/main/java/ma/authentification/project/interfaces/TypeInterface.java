package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Type;
import ma.authentification.project.exceptions.TypeException;

import java.util.List;

public interface TypeInterface {

    public List<Type> findAllTypes()throws TypeException;

    public Type findTypeById(Integer id)throws TypeException;

    public Type findTypeByName(String name)throws TypeException;

    public Type saveType(Type type)throws TypeException;

    public Type updateType(Type type)throws TypeException;

    public void deleteTypeById(Integer id)throws TypeException;

}
