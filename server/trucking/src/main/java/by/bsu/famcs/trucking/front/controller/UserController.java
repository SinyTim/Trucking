package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    private static final String PATH = "/api/{id}/users";
    private static final String ONE_USER_PATH = "/api/{id}";

    @Autowired
    private UserService userService;

    @GetMapping(path = ONE_USER_PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.findById(id));
    }

    @GetMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUsers(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.findAllForAdminId(id));
    }

    @PostMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addUser(@RequestBody UserBack user, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.addUser(user, id));
    }

    @PatchMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> changeUserStatus(@RequestBody UserBack user, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.changeUserStatus(user, id));
    }
}
