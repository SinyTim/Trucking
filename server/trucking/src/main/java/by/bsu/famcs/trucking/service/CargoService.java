package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.back.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<CargoBack> getAllCargoes(String userId) {
        return cargoRepository.findByOwnerId(userId);
    }

    public CargoBack addCargo(CargoBack cargo) { return cargoRepository.save(cargo); }

    public boolean deleteCargo(CargoBack cargo) {
        AtomicBoolean present = new AtomicBoolean(false);
        cargoRepository.findById(cargo.getId()).ifPresent(
                findCargo -> { present.set(true);
                cargoRepository.delete(findCargo);
                });
        return present.get();
    }
}
