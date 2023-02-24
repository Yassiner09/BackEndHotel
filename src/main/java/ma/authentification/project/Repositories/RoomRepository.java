package ma.authentification.project.Repositories;


import ma.authentification.project.entities.Room;
import ma.authentification.project.exceptions.RoomException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {

    List<Room> findAllByFloors(Integer floor) throws RoomException;

    Optional<List<Room>> findAllByAvailability(Boolean availability) ;

    List<Room> findAllByPrice(Double Price);


}
