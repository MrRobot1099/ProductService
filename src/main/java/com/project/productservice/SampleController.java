package com.project.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/say/{name}/{age}")
    public String sample(@PathVariable("name") String personName, @PathVariable("age") int personAge) {
        return "Say Hello to " + personName + "! Welcome to the world of Spring Boot!" + " Your age is " + personAge + " years.";
    }
}
