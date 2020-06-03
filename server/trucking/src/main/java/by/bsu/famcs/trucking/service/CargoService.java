package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.CargoRepository;
import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UserService userService;

    public CargoBack findById(String id) throws ResourceAccessDeniedException {
        AtomicReference<CargoBack> cargoBack = new AtomicReference<>(null);
        cargoRepository.findById(id).ifPresent(cargoBack::set);
        if (cargoBack.get() == null) {
            throw new ResourceAccessDeniedException("No cargo with such id");
        }
        return cargoBack.get();
    }

    public List<CargoBack> get(String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = userService.findById(id);
        if (user.getRole().equals(UserService.OWNER)) {
            return cargoRepository.findAllByOwnerId(id);
        } else {
            return cargoRepository.findAll();
        }
    }

    public CargoBack getCargo(String id, String cargoId) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = userService.findById(id);
        AtomicReference<CargoBack> present = new AtomicReference<>(null);
        cargoRepository.findById(cargoId).ifPresent(present::set);
        if (present.get() == null) {
            throw new ResourceAccessDeniedException("No cargo with such id");
        }

        if (user.getRole().equals(UserService.OWNER)) {
            if (!present.get().getOwnerId().equals(user.getId())) {
                throw new ResourceAccessException("You aren't the owner of this resource");
            }
        }
        return present.get();
    }

    public CargoBack post(CargoBack cargo, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = userService.findById(id);
        if (!user.getRole().equals(UserService.OWNER)) {
            throw new ResourceAccessDeniedException("Your role is not a OWNER, so you cannot create cargoes");
        }
        cargo.setOwnerId(id);
        return cargoRepository.save(cargo);
    }

    public void delete(CargoBack cargo, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        AtomicBoolean present = new AtomicBoolean(false);
        try {
            cargoRepository.findById(cargo.getId()).ifPresent(
                    findCargo -> {
                        present.set(true);
                        if (findCargo.getOwnerId().equals(id)) {
                            cargoRepository.delete(findCargo);
                        } else {
                            throw new ResourceAccessException("You aren't the owner of this resource");
                        }
                    });
        } catch (ResourceAccessException e) {
            throw new ResourceAccessDeniedException(e.getMessage());
        }
        if (!present.get()) {
            throw new ResourceAccessDeniedException("No cargo with such id");
        }
    }

    public CargoBack put(CargoBack cargo, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        delete(cargo, id);
        return post(cargo, id);
    }
}
