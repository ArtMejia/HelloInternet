package com.mejia.helloInternet.controllers;

import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
@org.springframework.web.bind.annotation.RestController

public class RestController {

    @GetMapping("/hello")
    private String helloCareerDevs() {
       return "You requested the root route";
    }

    @GetMapping("/login")
    private String login() {
        return "This is where you login";
    }

    @GetMapping("/random")
    private int randomNumber() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        n += 1;
        return n;
    }

    @GetMapping("/joke")
    private String randomJoke() {
        String[] jokes = {"How do you follow Will Smith in the snow? You follow the fresh prints.",
                "What do you call a fish wearing a bowtie? Sofishticated.",
                "I'm afraid for the calendar. Its days are numbered.",
                "Dear Math, grow up and solve your own problems."
        };
        Random r = new Random();
        int pickAJoke = r.nextInt(jokes.length);
        return jokes[pickAJoke];
    }

}