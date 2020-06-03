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
		UserBack owner2 = new UserBack("Owner2", "User", "owner2", "owner2", UserService.OWNER, false);
		UserBack carrier = new UserBack("Carrier", "User", "carrier", "carrier", UserService.CARRIER, false);
		UserBack carrier2 = new UserBack("Carrier2", "User", "carrier2", "carrier2", UserService.CARRIER, false);
		UserBack admin = new UserBack("Admin", "User", "admin", "admin", UserService.ADMIN, false);

		owner = authService.authorization(owner);
		owner2 = authService.authorization(owner2);
		carrier = authService.authorization(carrier);
		carrier2 = authService.authorization(carrier2);
		admin = authService.authorization(admin);

		CargoBack cargo1 = new CargoBack(owner.getId(), "Lamborghini",
				40, 60, 160, 60,
				"Minsk", "London", 666, "NEED_TO_DELIVER");
		CargoBack cargo2 = new CargoBack(owner.getId(), "Ferrari",
				80, 90, 200, 90,
				"Moscow", "Madrid", 333, "ALREADY_ON_DELIVERING");
		CargoBack cargo3 = new CargoBack(owner.getId(), "Bentley",
				80, 90, 200, 90,
				"Paris", "Prague", 100, "ALREADY_ON_DELIVERING");
		CargoBack cargo4 = new CargoBack(owner2.getId(), "Camaro",
				40, 60, 160, 60,
				"Vilnius", "Rome", 666, "ALREADY_ON_DELIVERING");
		CargoBack cargo5 = new CargoBack(owner2.getId(), "Challenger",
				40, 60, 160, 60,
				"Vilnius", "Rome", 666, "ALREADY_ON_DELIVERING");
		CargoBack cargo6 = new CargoBack(owner2.getId(), "Mustang",
				40, 60, 160, 60,
				"Vilnius", "Rome", 666, "ALREADY_ON_DELIVERING");

		cargo1 = cargoRepository.save(cargo1);
		cargo2 = cargoRepository.save(cargo2);
		cargo3 = cargoRepository.save(cargo3);
		cargo4 = cargoRepository.save(cargo4);
		cargo5 = cargoRepository.save(cargo5);
		cargo6 = cargoRepository.save(cargo6);

		List<String> route = new ArrayList<>();
		route.add(cargo1.getSource_location());
		route.add(cargo1.getDestination());
		route.add(cargo2.getSource_location());
		route.add(cargo2.getDestination());
		List<String> cargoes = new ArrayList<>();
		cargoes.add(cargo1.getId());
		cargoes.add(cargo2.getId());
		transportationRepository.save(new TransportationBack(carrier.getId(), route, route.get(0), cargoes));

		List<String> route2 = new ArrayList<>();
		route2.add(cargo3.getSource_location());
		route2.add(cargo3.getDestination());
		route2.add(cargo4.getSource_location());
		route2.add(cargo4.getDestination());
		List<String> cargoes2 = new ArrayList<>();
		cargoes2.add(cargo3.getId());
		cargoes2.add(cargo4.getId());
		transportationRepository.save(new TransportationBack(carrier.getId(), route2, route2.get(0), cargoes2));

		List<String> route3 = new ArrayList<>();
		route3.add(cargo5.getSource_location());
		route3.add(cargo5.getDestination());
		List<String> cargoes3 = new ArrayList<>();
		cargoes3.add(cargo5.getId());
		cargoes3.add(cargo6.getId());
		transportationRepository.save(new TransportationBack(carrier2.getId(), route3, route3.get(0), cargoes3));

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

