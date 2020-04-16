package by.bsu.famcs.trucking;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins={"http://localhost:3000"})
@RestController
public class HelloController {

    @RequestMapping("/api")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
