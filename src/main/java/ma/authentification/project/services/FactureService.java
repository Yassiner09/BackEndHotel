package ma.authentification.project.services;

import lombok.RequiredArgsConstructor;
import ma.authentification.project.Repositories.FactureRepository;
import ma.authentification.project.entities.Facture;
import ma.authentification.project.exceptions.FactureException;
import ma.authentification.project.interfaces.FactureInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FactureService implements FactureInterface {
    private final FactureRepository factureRepository;

    @Override
    public List<Facture> findAllFactures()  {
        return factureRepository.findAll();
    }


    @Override
    public Facture findFactureById(Integer id) throws FactureException {
        return factureRepository.findById(id).orElseThrow(()->new FactureException("Facture not found !"));
    }


    public Facture saveFacture(Facture facture){
        return factureRepository.save(facture);
    }
    public Facture updateFacture(Facture facture){
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(Integer id) throws FactureException {
        findFactureById(id);
        factureRepository.deleteById(id);
    }

}
