package ma.authentification.project.services;

import lombok.AllArgsConstructor;
import ma.authentification.project.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;


}
