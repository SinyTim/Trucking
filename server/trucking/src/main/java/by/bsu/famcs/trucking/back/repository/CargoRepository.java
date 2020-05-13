package by.bsu.famcs.trucking.back.repository;

import java.util.List;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CargoRepository extends MongoRepository<CargoBack, String> {

    public List<CargoBack> findByName(String name);

    public boolean existsByName(String name);

    public List<CargoBack> findByOwnerId(String ownerId);

}
