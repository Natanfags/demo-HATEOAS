package com.example.demohateoas;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class GreetingController {

    private final String TEMPLATE = "Hello, %s";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greetings(@RequestParam(value = "name", defaultValue = "World") String name) {

        final Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greetings(name)).withSelfRel());
        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }

}
