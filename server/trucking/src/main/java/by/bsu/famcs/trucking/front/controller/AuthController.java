package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.service.AuthService;
import by.bsu.famcs.trucking.front.entity.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class AuthController {

    private static final Logger LOGGER = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/api/authenticate",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + jwtRequest);
        return ResponseCreator.body(() -> authService.authenticate(jwtRequest.getUsername(), jwtRequest.getPassword()));
    }

    @PostMapping(path = "/api/authorization",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authorization(@RequestBody UserBack userBack) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + userBack);
        return ResponseCreator.body(() -> {
            UserBack authUser = authService.authorization(userBack);
            return authService.authenticate(authUser.getUsername(), authUser.getPassword());
        });
    }
}
