package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCargoes(@PathVariable String id) {
        System.out.println("<--> getCargoes " + id);
        return ResponseCreator.body(() -> cargoService.getAvailableCargoes(id));
    }

    @PostMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> addCargo " + id);
        return ResponseCreator.body(() -> cargoService.addCargo(cargo, id));
    }

    @DeleteMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> deleteCargo " + id);
        return ResponseCreator.body(() -> {
            cargoService.deleteCargo(cargo, id);
            return "Successfully deleted";
        });
    }

    @PutMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> updateCargo " + id);
        return ResponseCreator.body(() -> cargoService.updateCargo(cargo, id));
    }
}
