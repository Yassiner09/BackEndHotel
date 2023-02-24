package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Facture;
import ma.authentification.project.exceptions.FactureException;

import java.util.List;

public interface FactureInterface {


    List<Facture> findAllFactures() throws FactureException;

    Facture findFactureById(Integer id)throws FactureException;

    Facture saveFacture(Facture facture)throws FactureException;

    Facture updateFacture(Facture facture)throws FactureException;

    void deleteFacture(Integer id)throws FactureException;

}
