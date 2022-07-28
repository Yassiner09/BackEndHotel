package ma.authentification.project.controllers;


import lombok.AllArgsConstructor;
import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.RoomException;
import ma.authentification.project.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@AllArgsConstructor
@CrossOrigin
public class RoomController {

    private RoomService roomService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Room>> findAllRooms()throws RoomException {
        if(roomService.findAllRooms()==null) throw new RoomException("rooms not found!");
        return new ResponseEntity<>(roomService.findAllRooms(), HttpStatus.OK);
    }


    @GetMapping("/findById/{id}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Room> findRoomById(@PathVariable Integer id)throws RoomException {
        return new ResponseEntity<>(roomService.findRoomById(id), HttpStatus.OK);
    }


    @GetMapping("/findByFloor/{floor}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Room>> findRoomsByFloor(@PathVariable Integer floor)throws RoomException {
        return new ResponseEntity<>(roomService.findRoomsByFloors(floor), HttpStatus.OK);
    }


    @GetMapping("/findByAvailability/{availability}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Room>> findRoomsByAvailability(@PathVariable Boolean availability)throws RoomException {
        return new ResponseEntity<>(roomService.findRoomsByAvailability(availability), HttpStatus.OK);
    }

    @GetMapping("/findByPrice/{price}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Room>> findRoomsByPrice(@PathVariable Double price)throws RoomException {
        return new ResponseEntity<>(roomService.findRoomsByPrice(price), HttpStatus.OK);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room)throws RoomException {
        return new ResponseEntity<>(roomService.saveRoom(room), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('Admin')")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room)throws RoomException {
        return new ResponseEntity<>(roomService.updateRoom(room), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> deleteRoom(@PathVariable Integer id)throws RoomException {
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
