package ma.authentification.project.services;

import ma.authentification.project.Repositories.ReservationRepository;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.ReservationException;
import ma.authentification.project.exceptions.RoomException;
import ma.authentification.project.exceptions.ServiceException;
import ma.authentification.project.interfaces.ReservationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class ReservationService implements ReservationInterface {
    private Double totalPrice;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomService roomService;

    @Autowired
    private ServiceService serviceService;
    public List<Reservation> findAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Reservation reservation) throws Exception,ReservationException {
        Reservation res=findReservationById(reservation.getIdRes());
        Room room = roomService.findRoomById(reservation.getRoom().getIdRoom());
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
    public List<Reservation> findReservationsByDateBetween(LocalDate debutDate, LocalDate finalDate)throws ReservationException {
        return reservationRepository.findAllByDateBetween(debutDate,finalDate);
    }

    @Override
    public List<Reservation> findReservationsByDateAndTimeBetween(LocalDate date, LocalTime beginTime, LocalTime endTime)throws ReservationException {
        return reservationRepository.findAllByDateAndTimeBetween(date,beginTime,endTime);
    }

    @Override
    public List<Reservation> findReservationsByDuree(Integer duree) throws ReservationException {
        return  reservationRepository.findAllByDuree(duree);

    }

    @Override
    public Reservation saveReservation(Reservation reservation) throws ReservationException, RoomException {
        Room room = roomService.findRoomById(reservation.getRoom().getIdRoom());
        if(reservation.getRoom().getAvailability()!=false){
            room.setAvailability(false);
            reservation.setRoom(room);
        }
        return reservationRepository.save(reservation);
    }

    public void deleteReservationById(Integer id) throws ReservationException {
        this.findReservationById(id);
        reservationRepository.deleteById(id);
    }

    public Double totalPrice(Reservation reservation) throws ReservationException {
        totalPrice=0.;
        Reservation res = reservationRepository.findById(reservation.getIdRes()).orElseThrow(()->new ReservationException("Reservation not found !"));
        totalPrice+=res.getRoom().getPrice();
        totalPrice+=res.getServices().stream().map(s->s.getPrice()).mapToDouble(s->s).sum();
        return totalPrice;
    }
    public void updateFacture(Reservation reservation) throws ReservationException{
        Reservation res = reservationRepository.findById(reservation.getIdRes()).orElseThrow(()->new ReservationException("Reservation not found !"));
        res.getFacture().setTotalPrice(totalPrice(res));
    }

    public void addServiceToReservation(Integer idSer,Integer idRes) throws ReservationException, ServiceException {
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
