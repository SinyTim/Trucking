package by.bsu.famcs.trucking.service;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.UserRepository;
import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {
    public static final String ADMIN = "Admin";
    public static final String OWNER = "Owner";
    public static final String CARRIER = "Carrier";

    @Autowired
    private UserRepository userRepository;

    public UserBack findById(String userId) throws UserNotFoundException, ResourceAccessDeniedException {
        AtomicReference<UserBack> userBack = new AtomicReference<>(null);
        userRepository.findById(userId).ifPresent(userBack::set);
        if (userBack.get() == null) {
            throw new UserNotFoundException("No user with such id");
        }
        if (userBack.get().isBanned()) {
            throw new ResourceAccessDeniedException("Sorry, this person is banned");
        }
        return userBack.get();
    }

    public UserBack unsafeFindById(String userId) throws UserNotFoundException {
        AtomicReference<UserBack> userBack = new AtomicReference<>(null);
        userRepository.findById(userId).ifPresent(userBack::set);
        if (userBack.get() == null) {
            throw new UserNotFoundException("No user with such id");
        }
        return userBack.get();
    }

    public List<UserBack> findAllForAdminId(String adminId) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack admin = findById(adminId);
        if (!admin.getRole().equals(ADMIN)) {
            throw new ResourceAccessDeniedException("Sorry, only admin can view this page");
        }
        return userRepository.findAll();
    }

    public UserBack addUser(UserBack userBack, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = findById(id);
        if (!user.getRole().equals(UserService.ADMIN)) {
            throw new ResourceAccessDeniedException("Sorry, only admin can add new users");
        }
        return userRepository.save(userBack);
    }

    public UserBack changeUserStatus(UserBack userBack, String id) throws UserNotFoundException, ResourceAccessDeniedException {
        UserBack user = findById(id);
        if (!user.getRole().equals(UserService.ADMIN)) {
            throw new ResourceAccessDeniedException("Sorry, only admin can ban users");
        }
        UserBack userFind = unsafeFindById(userBack.getId());
        userFind.setBanned(userBack.isBanned());
        return userRepository.save(userFind);
    }
}
