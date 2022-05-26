package ma.authentification.project.exceptions;

import ma.authentification.project.entities.Facture;

public class FactureException extends Exception{

    public FactureException(String errorMessage){
        super(errorMessage);
    }

}
