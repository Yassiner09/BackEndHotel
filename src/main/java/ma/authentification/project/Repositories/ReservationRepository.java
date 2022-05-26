package ma.authentification.project.Repositories;

import ma.authentification.project.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface  ReservationRepository extends JpaRepository<Reservation,Integer> {

    public List<Reservation> findAllByDateBetween(LocalDate debutDate, LocalDate finalDate);

    public List<Reservation> findAllByDateAndTimeBetween(LocalDate date, LocalTime beginTime, LocalTime endTime);

    public List<Reservation> findAllByDuree(Integer duree);


}
