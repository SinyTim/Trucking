package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping(path = "/api/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getAllCargoes() {
        System.out.println("<--> getAllCargoes");
        return ResponseEntity.ok(cargoService.getAllCargoes());
    }

    @GetMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getCargoes(@PathVariable String id) {
        System.out.println("<--> getCargoes " + id);
        return ResponseEntity.ok(cargoService.getAllCargoesByOwnerId(id));
    }

    @PostMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> addCargo " + id);
        cargo.setOwnerId(id);
        cargo = cargoService.addCargo(cargo);
        return ResponseEntity.ok(cargo);
    }

    @DeleteMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> deleteCargo " + id);
        cargo.setOwnerId(id);
        try {
            cargoService.deleteCargo(cargo);
            return ResponseEntity.ok("Successfully deleted");
        } catch (ResourceAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> updateCargo " + id);
        cargo.setOwnerId(id);
        try {
            cargoService.deleteCargo(cargo);
            cargo = cargoService.addCargo(cargo);
            return ResponseEntity.ok(cargo);
        } catch (ResourceAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
