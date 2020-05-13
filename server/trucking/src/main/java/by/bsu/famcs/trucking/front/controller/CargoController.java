package by.bsu.famcs.trucking.front.controller;

import by.bsu.famcs.trucking.back.entity.CargoBack;
import by.bsu.famcs.trucking.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping(path = "/users/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CargoBack> getCargoes(@PathVariable String id) {
        return cargoService.getAllCargoes(id);
    }

    @PostMapping(path = "/users/{id}/cargo",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addCargo(@RequestBody CargoBack cargo, @PathVariable String id) {
        cargo.setOwnerId(id);
        cargoService.addCargo(cargo);
    }
}