package by.bsu.famcs.trucking.back.repository;

import by.bsu.famcs.trucking.back.entity.TransportationBack;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransportationRepository extends MongoRepository<TransportationBack, String> {

    public List<TransportationBack> findAllByCarrierId(String carrierId);

    public List<TransportationBack> findAllByCargoesContains(String cargoId);
}
