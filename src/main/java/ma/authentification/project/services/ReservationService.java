package ma.authentification.project.services;

import ma.authentification.project.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;


}
