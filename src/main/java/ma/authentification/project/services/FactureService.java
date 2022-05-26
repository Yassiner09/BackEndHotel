package ma.authentification.project.services;

import ma.authentification.project.Repositories.FactureRepository;
import ma.authentification.project.entities.Facture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FactureService {
    @Autowired
    private FactureRepository factureRepository;

    public Facture addFacture(Facture facture){
        return factureRepository.save(facture);
    }
    public Facture updateFacture(Facture facture){
        return factureRepository.save(facture);
    }

}
