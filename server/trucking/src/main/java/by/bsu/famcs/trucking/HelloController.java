package by.bsu.famcs.trucking;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class HelloController {

    @GetMapping("/api")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
