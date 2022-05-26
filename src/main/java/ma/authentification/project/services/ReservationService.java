package ma.authentification.project.services;

import ma.authentification.project.Repositories.ReservationRepository;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    private Double totalPrice;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomService roomService;

    @Autowired
    private ServiceService serviceService;
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }
    public Reservation addReservation(Reservation reservation) throws Exception {
        Room room = roomService.findRoomById(reservation.getRoom().getIdRoom());
        if(reservation.getRoom().getAvailability()!=false){
            room.setAvailability(false);
            reservation.setRoom(room);
        }
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation) throws ReservationException,Exception {
        Reservation res=findReservationById(reservation.getIdRes());
        Room room = roomService.findRoomById(reservation.getRoom().getIdRoom());
        if(res.getRoom()!=reservation.getRoom()){
            room.setAvailability(false);
            reservation.setRoom(room);
            res.getRoom().setAvailability(true);
        }
        return reservationRepository.save(reservation);
    }

    public Reservation findReservationById(Integer id) throws ReservationException{
        return reservationRepository.findById(id).orElseThrow(()->new ReservationException("reservation not found."));
    }

    public void deleteReservationById(Integer id) throws Exception {
        this.findReservationById(id);
        reservationRepository.deleteById(id);
    }

    public Double totalPrice(Reservation reservation) throws Exception{
        totalPrice=0.;
        Reservation res = reservationRepository.findById(reservation.getIdRes()).orElseThrow(()->new Exception("reservation not found"));
        totalPrice+=res.getRoom().getPrice();
        totalPrice+=res.getServices().stream().map(s->s.getPrice()).mapToDouble(s->s).sum();
        return totalPrice;
    }
    public void updateFacture(Reservation reservation) throws Exception{
        Reservation res = reservationRepository.findById(reservation.getIdRes()).orElseThrow(()->new ReservationException("reservation not found."));
        res.getFacture().setTotalPrice(totalPrice(res));
    }

    public void addServiceToReservation(Integer idSer,Integer idRes) throws Exception {
        Reservation reservation=findReservationById(idRes);
        ma.authentification.project.entities.Service service=serviceService.findServiceById(idSer);
        reservation.addServiceToReservation(service);
        updateFacture(reservation);
    }
}
