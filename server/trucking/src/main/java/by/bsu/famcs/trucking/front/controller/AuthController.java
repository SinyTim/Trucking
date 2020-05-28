package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.UserBack;
import by.bsu.famcs.trucking.front.entity.UserAuthResponseFront;
import by.bsu.famcs.trucking.service.AuthService;
import by.bsu.famcs.trucking.front.entity.JwtRequest;
import by.bsu.famcs.trucking.service.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class AuthController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/api/authenticate",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("--> authenticate " + authenticationRequest);
        UserAuthResponseFront user = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
        user.setToken(token);
        System.out.println("<-- authenticate " + user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "/api/authorization",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> authorization(@RequestBody UserBack authUser) throws Exception {
        System.out.println("--> authorization " + authUser);
        authService.authorization(authUser);

        UserAuthResponseFront user = authenticate(authUser.getUsername(), authUser.getPassword());
        final String token = jwtTokenUtil.generateToken(authUser.getUsername());
        user.setToken(token);

        System.out.println("<-- authorization " + user);
        return ResponseEntity.ok(user);
    }

    private UserAuthResponseFront authenticate(String username, String password) throws Exception {
        try {
            return authService.authenticate(username, password);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
