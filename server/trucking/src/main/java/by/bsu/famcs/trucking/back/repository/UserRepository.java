package by.bsu.famcs.trucking.back.repository;

import by.bsu.famcs.trucking.back.entity.UserBack;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserBack, String> {

    public UserBack findByUsername(String name);

    public boolean existsByUsername(String name);

    public Optional<UserBack> findById(String id);
}
