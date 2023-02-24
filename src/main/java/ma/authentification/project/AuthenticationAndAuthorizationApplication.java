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
	@Autowired
	FidelityRepository fidelityRepository;

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

		Type t2=new Type();
		t2.setName("simple");
		t2.setDescription("miw");
		typeRepository.save(t2);

		Room r1=new Room(null,"100",12,true,1500.,t1,"+21233333332", new ArrayList<>());
		roomRepository.save(r1);

		Room r2=new Room(null,"101",12,true,2000.,t1,"+21233223333", new ArrayList<>());
		roomRepository.save(r2);

		Room r3=new Room(null,"112",2,true,2000.,t1,"+212374939293", new ArrayList<>());
		roomRepository.save(r3);

		Room r4=new Room(null,"106",4,true,750.,t2,"+212032839233", new ArrayList<>());
		roomRepository.save(r4);


		Fidelity fidelity1=new Fidelity(null,"aucune",0.,0.,new ArrayList<>());
		fidelityRepository.save(fidelity1);

		Fidelity fidelity=new Fidelity(null,"fidelity1",15.,10.,new ArrayList<>());
		fidelityRepository.save(fidelity);



		Client client1=new Client(null,"yassine","elh","AS2433","marocain","23134355321121",fidelity,new ArrayList<>());
		Client client2=new Client(null,"yass","hihi","AS4433","marocain","23333333333333",fidelity1,new ArrayList<>());
		clientRepository.save(client1);
		clientRepository.save(client2);

		Service service=new Service();
		service.setDescription("wow");
		service.setName("spa");
		service.setPrice(1500.);
		serviceRepository.save(service);

		Reservation reservation=new Reservation(null, LocalDate.of(2022,12,21), LocalTime.of(12, 0),12,client1,r1,new ArrayList<>(),new Facture(),u);
		reservationService.saveReservation(reservation);
		reservationService.updateFacture(reservation);

		Reservation reservation2=new Reservation(null, LocalDate.of(2023,9,11), LocalTime.of(12, 0),12,client2,r2,new ArrayList<>(),new Facture(),u);
		reservationService.saveReservation(reservation2);
		reservationService.updateFacture(reservation2);



	}
}
