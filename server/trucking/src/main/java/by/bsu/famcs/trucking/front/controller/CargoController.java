package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.exceptions.ResourceAccessDeniedException;
import by.bsu.famcs.trucking.exceptions.UserNotFoundException;
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
        try {
            return ResponseEntity.ok(cargoService.getAvailableCargoes(id));
        } catch (ResourceAccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> addCargo " + id);
        try {
            return ResponseEntity.ok(cargoService.addCargo(cargo, id));
        } catch (ResourceAccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> deleteCargo " + id);
        try {
            cargoService.deleteCargo(cargo, id);
            return ResponseEntity.ok("Successfully deleted");
        } catch (ResourceAccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/api/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        System.out.println("<--> updateCargo " + id);
        try {
            cargoService.deleteCargo(cargo, id);
            return ResponseEntity.ok(cargoService.addCargo(cargo, id));
        } catch (ResourceAccessDeniedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
