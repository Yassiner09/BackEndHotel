package ma.authentification.project.services;

import ma.authentification.project.Repositories.RoomRepository;
import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.RoomException;
import ma.authentification.project.interfaces.RoomInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService implements RoomInterface {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAllRooms(){
        return roomRepository.findAll();
    }

    public Room findRoomById(Integer id) throws RoomException {
        return roomRepository.findById(id).orElseThrow(()->new RoomException("Room with"+id+" not found !"));
    }

    @Override
    public List<Room> findRoomsByFloors(Integer floor) throws RoomException {
        return roomRepository.findAllByFloors(floor);
    }

    @Override
    public List<Room> findRoomsByAvailability(Boolean availability) throws RoomException {
        //need to specify the date
        return roomRepository.findAllByAvailability(availability);
    }

    @Override
    public List<Room> findRoomsByPrice(Double price) throws RoomException {
        return roomRepository.findAllByPrice(price);
    }

    public Room saveRoom(Room room) throws RoomException
    {
        return roomRepository.save(room);
    }
    public Room updateRoom(Room room)throws RoomException
    {
        return roomRepository.save(room);
    }
    public void deleteRoomById(Integer id) throws RoomException{
        roomRepository.deleteById(id);
    }
}
