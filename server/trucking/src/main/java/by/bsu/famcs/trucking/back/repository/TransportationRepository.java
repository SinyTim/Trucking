package by.bsu.famcs.trucking.back.repository;

import by.bsu.famcs.trucking.back.entity.TransportationBack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransportationRepository extends MongoRepository<TransportationBack, String> {

}
