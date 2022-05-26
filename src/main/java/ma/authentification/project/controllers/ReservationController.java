package ma.authentification.project.controllers;

import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Reservation;
import ma.authentification.project.entities.Service;
import ma.authentification.project.services.ReservationService;
import ma.authentification.project.services.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservation")
@AllArgsConstructor
public class ReservationController {
    private ReservationService reservationService;
    private ServiceService serviceService;

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Reservation>> findAll(){
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}/reservation")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Reservation> findReservationById(@PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(reservationService.findReservationById(id),HttpStatus.OK);
    }


    @PostMapping("add")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) throws Exception {
        return new ResponseEntity<>(reservationService.addReservation(reservation),HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) throws Exception {
        return new ResponseEntity<>(reservationService.updateReservation(reservation),HttpStatus.CREATED);
    }

    @PostMapping("{idRes}/service/{idSer}")
    public ResponseEntity<?> addServiceToReservation(@PathVariable Integer idRes,@PathVariable Integer idSer) throws Exception {
        Reservation reservation = reservationService.findReservationById(idRes);
        Service service = serviceService.findServiceById(idSer);
        reservationService.addServiceToReservation(service.getIdService(),reservation.getIdRes());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
