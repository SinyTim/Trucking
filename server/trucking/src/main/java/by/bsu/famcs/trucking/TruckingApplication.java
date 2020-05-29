package by.bsu.famcs.trucking;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.back.entity.TransportationBack;
import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.CargoRepository;
import by.bsu.famcs.trucking.back.repository.TransportationRepository;
import by.bsu.famcs.trucking.back.repository.UserRepository;
import by.bsu.famcs.trucking.service.AuthService;
import by.bsu.famcs.trucking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@SpringBootApplication
public class TruckingApplication implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(TruckingApplication.class.getName());

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

		UserBack owner = new UserBack("Owner", "User", "owner", "owner", UserService.OWNER, false);
		UserBack carrier = new UserBack("Carrier", "User", "carrier", "carrier", UserService.CARRIER, false);
		UserBack admin = new UserBack("Admin", "User", "admin", "admin", UserService.ADMIN, false);
		owner = authService.authorization(owner);
		carrier = authService.authorization(carrier);
		admin = authService.authorization(admin);

		CargoBack cargo1 = new CargoBack(owner.getId(), "Alice",
				40, 60, 160, 60,
				"Minsk", "London", 666, "NEED_TO_DELIVER");
		CargoBack cargo2 = new CargoBack(owner.getId(), "Bob",
				80, 90, 200, 90,
				"Moscow", "London", 333, "ALREADY_ON_DELIVERING");
		cargo1 = cargoRepository.save(cargo1);
		cargo2 = cargoRepository.save(cargo2);

		List<String> route = new ArrayList<>();
		route.add("Moscow");
		route.add("Minsk");
		route.add("London");
		List<String> cargoes = new ArrayList<>();
		cargoes.add(cargo2.getId());
		transportationRepository.save(new TransportationBack(carrier.getId(), route, "Moscow", cargoes));

		for (CargoBack cargoBack : cargoRepository.findAll()) {
			LOGGER.info(cargoBack.toString());
		}

		for (UserBack userBack : userRepository.findAll()) {
			LOGGER.info(userBack.toString());
		}

		for (TransportationBack transportationBack : transportationRepository.findAll()) {
			LOGGER.info(transportationBack.toString());
		}
	}
}

