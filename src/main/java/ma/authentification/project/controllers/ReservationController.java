package ma.authentification.project.controllers;

import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.Service;
import ma.authentification.project.exceptions.ReservationException;
import ma.authentification.project.services.ReservationService;
import ma.authentification.project.services.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("api/reservation")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;
    private ServiceService serviceService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> findAllReservations() {
        return new ResponseEntity<>(reservationService.findAllReservations(), HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Reservation> findReservationById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(reservationService.findReservationById(id), HttpStatus.OK);
    }

    @GetMapping("find/{beginDate}/{endDate}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> findReservationsByDateBetween(@PathVariable LocalDate beginDate,@PathVariable LocalDate endDate)throws ReservationException{
        return new ResponseEntity<>(reservationService.findReservationsByDateBetween(beginDate,endDate),HttpStatus.OK);
    }
    @GetMapping("find/{date}/{beginDate}/{endDate}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> findReservationsByDateAndTimeBetween(@PathVariable LocalDate date, @PathVariable LocalTime beginTime,@PathVariable LocalTime endTime)throws ReservationException{
        return new ResponseEntity<>(reservationService.findReservationsByDateAndTimeBetween(date,beginTime,endTime),HttpStatus.OK);
    }

    @GetMapping("find/{duree}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> findReservationsByDuree(@PathVariable Integer duree) throws ReservationException {
        return new ResponseEntity<>(reservationService.findReservationsByDuree(duree),HttpStatus.OK);
    }


    @PostMapping("add")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation) throws Exception {
        return new ResponseEntity<>(reservationService.saveReservation(reservation), HttpStatus.CREATED);
    }

    @PutMapping("update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) throws Exception {
        return new ResponseEntity<>(reservationService.updateReservation(reservation), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<?> deleteReservationById(@PathVariable Integer id)throws ReservationException{
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("{idRes}/service/{idSer}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<?> addServiceToReservation(@PathVariable Integer idRes,@PathVariable Integer idSer) throws Exception {
        Reservation reservation = reservationService.findReservationById(idRes);
        Service service = serviceService.findServiceById(idSer);
        reservationService.addServiceToReservation(service.getIdService(),reservation.getIdRes());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
