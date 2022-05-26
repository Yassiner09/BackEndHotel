package ma.authentification.project.services;

import ma.authentification.project.Repositories.FactureRepository;
import ma.authentification.project.entities.Facture;
import ma.authentification.project.exceptions.FactureException;
import ma.authentification.project.interfaces.FactureInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class FactureService implements FactureInterface {
    @Autowired
    private FactureRepository factureRepository;

    @Override
    public List<Facture> findAllFactures() throws FactureException {
        return factureRepository.findAll();
    }


    @Override
    public Facture findFactureById(Integer id) throws FactureException {
        return factureRepository.findById(id).orElseThrow(()->new FactureException("Facture not found !"));
    }


    public Facture saveFacture(Facture facture)throws FactureException{
        return factureRepository.save(facture);
    }
    public Facture updateFacture(Facture facture)throws FactureException{
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(Integer id) throws FactureException {
        findFactureById(id);
        factureRepository.deleteById(id);
    }

}
