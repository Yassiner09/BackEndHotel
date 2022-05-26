package ma.authentification.project;

import ma.authentification.project.Repositories.*;
import ma.authentification.project.entities.*;
import ma.authentification.project.services.ReservationService;
import ma.authentification.project.services.RoleService;
import ma.authentification.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenticationAndAuthorizationApplication implements CommandLineRunner {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	TypeRepository typeRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ReservationService reservationService;
	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ServiceRepository serviceRepository;

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationAndAuthorizationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role rA=new Role();
		rA.setName("Admin");
		roleService.addRole(rA);
		Role rU=new Role();
		rU.setName("User");
		roleService.addRole(rU);

		User u=new User(null,"yassine","haha123",new HashSet<>(),new ArrayList<>());
		u.addRoleToUser(rA);
		userService.registerNewUserWithRole(u);

		Type t1=new Type();
		t1.setName("suite");
		t1.setDescription("wow");
		typeRepository.save(t1);

		Room r1=new Room(null,"100",12,true,1500.,t1,"+21233333332");
		roomRepository.save(r1);

		Room r2=new Room(null,"101",12,true,2000.,t1,"+21233223333");
		roomRepository.save(r2);

		Client client1=new Client(null,"yassine","elh","AS2433","marocain","23134355321121",false,new ArrayList<>());
		clientRepository.save(client1);

		Service service=new Service();
		service.setDescription("wow");
		service.setName("spa");
		service.setPrice(1500.);
		serviceRepository.save(service);

		Reservation reservation=new Reservation(null, LocalDate.of(2022,12,21), LocalTime.of(12,00),12,client1,r1,new ArrayList<>(),new Facture(),u);
		reservationService.addReservation(reservation);
		reservationService.updateFacture(reservation);







	}
}
