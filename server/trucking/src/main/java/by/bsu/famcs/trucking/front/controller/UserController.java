package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/api/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable String id) {
        System.out.println("<--> getUser " + id);
        return ResponseCreator.body(() -> userService.unsafeFindById(id));
    }

    @GetMapping(path = "/api/{id}/users",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUsers(@PathVariable String id) {
        System.out.println("<--> getUsers " + id);
        return ResponseCreator.body(() -> userService.findAllForAdminId(id));
    }

    @PostMapping(path = "/api/{id}/users",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addUser(@RequestBody UserBack user, @PathVariable String id) {
        System.out.println("<--> addUser " + id);
        return ResponseCreator.body(() -> userService.addUser(user, id));
    }

    @PatchMapping(path = "/api/{id}/users",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> changeUserStatus(@RequestBody UserBack user, @PathVariable String id) {
        System.out.println("<--> changeUserStatus " + id);
        return ResponseCreator.body(() -> userService.changeUserStatus(user, id));
    }
}
