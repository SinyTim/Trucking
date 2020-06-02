package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.TransportationBack;
import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.TransportationRepository;
import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class TransportationService {

    @Autowired
    private TransportationRepository transportationRepository;

    @Autowired
    private UserService userService;

    public List<TransportationBack> get(String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = userService.findById(id);
        if (user.getRole().equals(UserService.ADMIN)) {
            return transportationRepository.findAll();
        } else if (user.getRole().equals(UserService.CARRIER)) {
            return transportationRepository.findAllByCarrierId(id);
        } else {
            return transportationRepository.findAll().stream().filter(
                    transportationBack -> transportationBack.getCargoes().stream().anyMatch(id::equals)
            ).collect(Collectors.toList());
        }
    }

    public TransportationBack post(TransportationBack transportationBack, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = userService.findById(id);
        if (!user.getRole().equals(UserService.CARRIER)) {
            throw new ResourceAccessDeniedException("Your role is not a CARRIER, so you cannot create a transportation");
        }
        transportationBack.setCarrierId(user.getId());
        return transportationRepository.save(transportationBack);
    }

    public void delete(TransportationBack transportationBack, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        AtomicBoolean present = new AtomicBoolean(false);
        try {
            transportationRepository.findById(transportationBack.getId()).ifPresent(
                    findTransportation -> {
                        present.set(true);
                        if (findTransportation.getCarrierId().equals(id)) {
                            transportationRepository.delete(findTransportation);
                        } else {
                            throw new ResourceAccessException("You aren't the owner of this resource");
                        }
                    });
        } catch (ResourceAccessException e) {
            throw new ResourceAccessDeniedException(e.getMessage());
        }
        if (!present.get()) {
            throw new UserNotFoundException("No transportation with such id");
        }
    }

    public TransportationBack put(TransportationBack transportationBack, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        delete(transportationBack, id);
        return post(transportationBack, id);
    }
}
