package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private UserService userService;

    public UserBack get(String adminId) throws UserNotFoundException, ResourceAccessDeniedException {
        return userService.findById(adminId);
    }
}
