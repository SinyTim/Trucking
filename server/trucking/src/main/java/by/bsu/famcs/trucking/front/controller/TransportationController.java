package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.TransportationBack;
import by.bsu.famcs.trucking.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@CrossOrigin(origins={"http://localhost:3000"})
@RestController
@RequestMapping(path = "/api/{id}/transportation",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class TransportationController {

    private static final Logger LOGGER = Logger.getLogger(TransportationController.class.getName());

    @Autowired
    private TransportationService transportationService;

    @GetMapping
    public ResponseEntity<?> get(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> transportationService.get(id));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TransportationBack transportationBack, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> transportationService.post(transportationBack, id));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody TransportationBack transportationBack, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.emptyBody(() -> transportationService.delete(transportationBack, id));
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody TransportationBack transportationBack, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> transportationService.put(transportationBack, id));
    }
}
