package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.back.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<CargoBack> getAllCargoesByOwnerId(String userId) {
        return cargoRepository.findByOwnerId(userId);
    }

    public List<CargoBack> getAllCargoes() {
        return cargoRepository.findAll();
    }

    public CargoBack addCargo(CargoBack cargo) { return cargoRepository.save(cargo); }

    public void deleteCargo(CargoBack cargo) throws NotFoundException, ResourceAccessException {
        AtomicBoolean present = new AtomicBoolean(false);
        cargoRepository.findById(cargo.getId()).ifPresent(
                findCargo -> {
                    present.set(true);
                    if (findCargo.getOwnerId().equals(cargo.getOwnerId())) {
                        cargoRepository.delete(findCargo);
                    } else {
                        throw new ResourceAccessException("You aren't the owner of this resource");
                    }
                });
        if (!present.get()) {
            throw new NotFoundException("No cargo with such id");
        }
    }
}
