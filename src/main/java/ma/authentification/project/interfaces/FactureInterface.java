package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Facture;
import ma.authentification.project.exceptions.FactureException;
import ma.authentification.project.exceptions.ReservationException;

import java.time.LocalDate;
import java.util.List;

public interface FactureInterface {


    public List<Facture> findAllFactures() throws FactureException;

    public Facture findFactureById(Integer id)throws FactureException;

    public Facture saveFacture(Facture facture)throws FactureException;

    public Facture updateFacture(Facture facture)throws FactureException;

    public void deleteFacture(Integer id)throws FactureException;

}
