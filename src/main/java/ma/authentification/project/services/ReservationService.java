package ma.authentification.project.services;

import lombok.RequiredArgsConstructor;
import ma.authentification.project.Repositories.ReservationRepository;
import ma.authentification.project.entities.Fidelity;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.ReservationException;
import ma.authentification.project.exceptions.RoomException;
import ma.authentification.project.exceptions.ServiceException;
import ma.authentification.project.interfaces.ReservationInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService implements ReservationInterface {

    private final ReservationRepository reservationRepository;

    private final RoomService roomService;

    private final ServiceService serviceService;
    public List<Reservation> findAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Reservation reservation
    ) throws Exception {
        Reservation res=findReservationById(reservation.getIdRes());
        Room room = roomService.findRoomById(reservation.getRoom().getRoomId());
        if(res.getRoom()!=reservation.getRoom()){
            room.setAvailability(false);
            reservation.setRoom(room);
            res.getRoom().setAvailability(true);
        }
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationById(Integer id) throws ReservationException {
        return reservationRepository.findById(id).orElseThrow(()->new ReservationException("Reservation not found."));
    }

    @Override
    public List<Reservation> findReservationsByDateBetween(LocalDate debutDate, LocalDate finalDate) {
        return reservationRepository.findAllByDateBetween(debutDate,finalDate);
    }

    @Override
    public List<Reservation> findReservationsByDateAndTimeBetween(LocalDate date, LocalTime beginTime, LocalTime endTime) {
        return reservationRepository.findAllByDateAndTimeBetween(date,beginTime,endTime);
    }

    @Override
    public List<Reservation> findReservationsByDuree(Integer duree) {
        return  reservationRepository.findAllByDuree(duree);

    }

    @Override
    public Reservation saveReservation(Reservation reservation) throws RoomException {
        Room room = roomService.findRoomById(reservation.getRoom().getRoomId());
        if(reservation.getRoom().getAvailability()){
            room.setAvailability(false);
            reservation.setRoom(room);
            return reservationRepository.save(reservation);
        }
        else{
            throw new RoomException("Room is not available");
        }
    }

    public void deleteReservationById(Integer id) throws ReservationException {
        Reservation reservation=findReservationById(id);
        reservation.getRoom().setAvailability(true);
        reservationRepository.deleteById(id);
    }

    public Double totalPrice(Reservation reservation) throws ReservationException {
        double totalPrice = 0.;
        Reservation res = reservationRepository.findById(reservation.getIdRes()).orElseThrow(()->new ReservationException("Reservation not found !"));
        Fidelity fidelity=reservation.getClient().getFidelity();
        if(fidelity!=null){
            totalPrice +=(reservation.getRoom().getPrice() - (reservation.getRoom().getPrice() * fidelity.getPercentageRoom() / 100)) * res.getDuree();
            totalPrice +=res.getServices().stream().map(s->s.getPrice() - (s.getPrice() * fidelity.getPercentageServices() / 100)).mapToDouble(s->s).sum();
        }
        else{
            totalPrice +=reservation.getRoom().getPrice() * res.getDuree();
            totalPrice +=res.getServices().stream().map(ma.authentification.project.entities.Service::getPrice).mapToDouble(s->s).sum();
        }
        return totalPrice;
    }
    public void updateFacture(Reservation reservation) throws ReservationException{
        Reservation res = reservationRepository.findById(reservation.getIdRes()).orElseThrow(()->new ReservationException("Reservation not found !"));
        res.getFacture().setTotalPrice(totalPrice(res));
    }

    public void addServiceToReservation(Integer idSer,Integer idRes) throws ReservationException, ServiceException {
        //add log file
        Reservation reservation=findReservationById(idRes);
        ma.authentification.project.entities.Service service=serviceService.findServiceById(idSer);
        reservation.addServiceToReservation(service);
        updateFacture(reservation);
    }

    @Override
    public void removeServiceFromReservation(Integer idSer, Integer idRes) throws ReservationException,ServiceException {

        Reservation reservation=findReservationById(idRes);
        ma.authentification.project.entities.Service service=serviceService.findServiceById(idSer);
        reservation.removeServiceFromReservation(service);
        updateFacture(reservation);
    }
}
