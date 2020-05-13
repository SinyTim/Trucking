package by.bsu.famcs.trucking;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.CargoRepository;
import by.bsu.famcs.trucking.back.repository.TransportationRepository;
import by.bsu.famcs.trucking.back.repository.UserRepository;
import by.bsu.famcs.trucking.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TruckingApplication implements CommandLineRunner {
	@Autowired
	private AuthService authService;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransportationRepository transportationRepository;

	public static void main(String[] args) {
		SpringApplication.run(TruckingApplication.class, args);
	}

	@Override
	public void run(String... args) {
		cargoRepository.deleteAll();
		userRepository.deleteAll();
		transportationRepository.deleteAll();

		UserBack user = new UserBack("Normal", "User", "user", "user", "User");
		UserBack admin = new UserBack("Admin", "User", "admin", "admin", "Admin");
		authService.authorization(user);
		authService.authorization(admin);

		cargoRepository.save(new CargoBack("owner1", "Alice",
				40, 60, 160, 60,
				"Minsk", "London", 666));
		cargoRepository.save(new CargoBack("owner1", "Bob",
				80, 90, 200, 90,
				"Moscow", "London", 333));

		for (CargoBack cargoBack : cargoRepository.findAll()) {
			System.out.println(cargoBack);
		}

		for (UserBack userBack : userRepository.findAll()) {
			System.out.println(userBack);
		}
	}
}

