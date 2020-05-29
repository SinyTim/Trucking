package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
import by.bsu.famcs.trucking.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class TransportationController {
    @Autowired
    private TransportationService transportationService;

    @GetMapping(path = "/api/{id}/transportation",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTransportations(@PathVariable String id) {
        System.out.println("<--> getTransportation " + id);
        try {
            return ResponseEntity.ok(transportationService.getAllTransportations(id));
        } catch (ResourceAccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
