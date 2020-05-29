package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.TransportationBack;
import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.TransportationRepository;
import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportationService {

    @Autowired
    private TransportationRepository transportationRepository;

    @Autowired
    private UserService userService;

    public List<TransportationBack> getAllTransportations(String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = userService.findById(id);
        if (user.getRole().equals(UserService.ADMIN)) {
            return transportationRepository.findAll();
        } else if (user.getRole().equals(UserService.CARRIER)) {
            return transportationRepository.findAllByCarrierId(id);
        } else {
            // TODO
            return transportationRepository.findAll();
        }
    }
}
