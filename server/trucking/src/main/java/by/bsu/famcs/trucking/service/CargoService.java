package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.back.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<CargoBack> getAllCargoes(String userId) {
        return cargoRepository.findByOwnerId(userId);
    }

    public void addCargo(CargoBack cargo) { cargoRepository.save(cargo); }
}
