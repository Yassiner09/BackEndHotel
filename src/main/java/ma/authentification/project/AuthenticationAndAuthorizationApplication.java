package ma.authentification.project;

import ma.authentification.project.Repositories.RoleRepository;
import ma.authentification.project.entities.Role;
import ma.authentification.project.entities.User;
import ma.authentification.project.services.RoleService;
import ma.authentification.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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


	}
}
