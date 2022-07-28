package ma.authentification.project.interfaces;

import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.RoomException;

import java.util.List;

public interface RoomInterface {

    public List<Room> findAllRooms() throws RoomException   ;

    public Room findRoomById(Integer id) throws RoomException;

    public List<Room> findRoomsByFloors(Integer floor) throws RoomException;

    public  List<Room> findRoomsByAvailability(Boolean availability)throws RoomException;

    public  List<Room> findRoomsByPrice(Double price)throws RoomException;

    public Room saveRoom(Room room) throws RoomException;

    public Room updateRoom(Room room) throws RoomException;

    public void deleteRoomById(Integer id ) throws RoomException;

}
