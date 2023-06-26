package com.example.holacodespaceS6;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path="/")
public class Controller {
    @GetMapping
    public String home() {
        return "PT76534813 - Ray Arturo Vargas Ore";
    }
}
