package ma.authentification.project.services;

import ma.authentification.project.Repositories.RoomRepository;
import ma.authentification.project.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAllRooms(){
        return roomRepository.findAll();
    }

    public Room findRoomById(Integer id) throws Exception {
        return roomRepository.findById(id).orElseThrow(()->new Exception("fuck"));
    }

    public Room addRoom(Room room){
        return roomRepository.save(room);
    }
    public Room updateRoom(Room room){
        return roomRepository.save(room);
    }
    public void deleteRoom(Integer id){
        roomRepository.deleteById(id);
    }
}
