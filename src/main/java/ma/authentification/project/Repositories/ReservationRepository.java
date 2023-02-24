package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface  ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findAllByDateBetween(LocalDate debutDate, LocalDate finalDate);

    List<Reservation> findAllByDateAndTimeBetween(LocalDate date, LocalTime beginTime, LocalTime endTime);

    List<Reservation> findAllByDuree(Integer duree);


}
