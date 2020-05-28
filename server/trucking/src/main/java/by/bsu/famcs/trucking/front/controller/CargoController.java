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

    @GetMapping(path = "/api/users/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllCargoes() {
        System.out.println("<--> getAllCargoes");
        return ResponseEntity.ok(cargoService.getAllCargoes());
    }

    @GetMapping(path = "/api/users/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCargoes(@PathVariable String id) {
        System.out.println("<--> getCargoes " + id);
        return ResponseEntity.ok(cargoService.getAllCargoesByOwnerId(id));
    }

    @PostMapping(path = "/api/users/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> addCargo " + id);
        cargo.setOwnerId(id);
        cargo = cargoService.addCargo(cargo);
        return ResponseEntity.ok(cargo);
    }

    @DeleteMapping(path = "/api/users/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> deleteCargo " + id);
        if (cargoService.deleteCargo(cargo)) {
            return ResponseEntity.ok("Successfully deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/api/users/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> updateCargo " + id);
        cargo.setOwnerId(id);
        if (cargoService.deleteCargo(cargo)) {
            cargo = cargoService.addCargo(cargo);
            return ResponseEntity.ok(cargo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
