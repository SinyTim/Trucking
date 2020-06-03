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
@RequestMapping(path = "/api/{id}/cargo",
        consumes = {MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
public class CargoController {

    private static final Logger LOGGER = Logger.getLogger(CargoController.class.getName());

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<?> get(@PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.get(id));
    }

    @GetMapping(path = "/{cargoId}")
    public ResponseEntity<?> getCargo(@PathVariable("id") String id, @PathVariable("cargoId") String cargoId) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.getCargo(id, cargoId));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody CargoBack cargo, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.post(cargo, id));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody CargoBack cargo, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.emptyBody(() -> cargoService.delete(cargo, id));
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody CargoBack cargo, @PathVariable String id) {
        LOGGER.info("<--> " + Thread.currentThread().getStackTrace()[1] + " " + id);
        return ResponseCreator.body(() -> cargoService.put(cargo, id));
    }
}
