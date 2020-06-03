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
@RequestMapping(path = "/api/{id}/users",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> get(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.get(id));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody UserBack user, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.post(user, id));
    }

    @PatchMapping
    public ResponseEntity<?> patchStatus(@RequestBody UserBack user, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> userService.patchStatus(user, id));
    }
}
