package by.bsu.famcs.trucking.front.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthCheckController {

    @GetMapping("/")
    public String index() {
        return "API works.";
    }
}
