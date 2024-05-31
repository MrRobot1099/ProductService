package com.project.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {
    @GetMapping("/{name}/{number}")
    public String sayHello(@PathVariable("name") String name, @PathVariable("number") int number) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= number; i++) {
            stringBuilder.append("Hello " + name + "\n");
        }
        //return "Hello " + name;
        return stringBuilder.toString();
    }
}
