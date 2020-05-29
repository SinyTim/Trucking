package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class TransportationController {

    private static final Logger LOGGER = Logger.getLogger(TransportationController.class.getName());

    private static final String PATH = "/api/{id}/transportation";

    @Autowired
    private TransportationService transportationService;

    @GetMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getTransportations(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> transportationService.getAllTransportations(id));
    }
}
