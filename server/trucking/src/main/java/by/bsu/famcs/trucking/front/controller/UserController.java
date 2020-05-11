package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"https://trucking-famcs.herokuapp.com"})
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserBack> getUsers() {
        System.out.println("<--> getUsers");
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Optional<UserBack> getUser(@PathVariable String id) {
        System.out.println("<--> getUser " + id);
        return userRepository.findById(id);
    }
}
