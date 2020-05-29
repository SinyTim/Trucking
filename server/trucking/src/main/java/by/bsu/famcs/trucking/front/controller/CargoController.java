package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class CargoController {

    private static final Logger LOGGER = Logger.getLogger(CargoController.class.getName());

    private static final String PATH = "/api/{id}/cargo";

    @Autowired
    private CargoService cargoService;

    @GetMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCargoes(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.getAvailableCargoes(id));
    }

    @PostMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.addCargo(cargo, id));
    }

    @DeleteMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> {
            cargoService.deleteCargo(cargo, id);
            return "Successfully deleted";
        });
    }

    @PutMapping(path = PATH,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.updateCargo(cargo, id));
    }
}
