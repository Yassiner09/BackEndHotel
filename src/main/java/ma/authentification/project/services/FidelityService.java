package ma.authentification.project.services;

import ma.authentification.project.Repositories.FidelityRepository;
import ma.authentification.project.entities.Fidelity;
import ma.authentification.project.exceptions.FidelityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FidelityService {
    @Autowired
    private FidelityRepository fidelityRepository;

    public List<Fidelity> getAllFidelities() {
        return fidelityRepository.findAll();
    }

    public Fidelity findFidelityById(Integer id) throws FidelityException {
        return fidelityRepository.findById(id).orElseThrow(()-> new FidelityException("Fidelity not found"));
    }
    public Fidelity findFidelityByName(String name) throws FidelityException {
        return fidelityRepository.findByName(name).orElseThrow(()-> new FidelityException("Fidelity not found"));
    }
    public Fidelity addFidelity(Fidelity fidelity){
        return fidelityRepository.save(fidelity);
    }
    public Fidelity updateFidelity(Fidelity fidelity){
        return fidelityRepository.save(fidelity);
    }
}
