package com.mejia.helloInternet.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// add the @RestController annotation to the newly created class
@org.springframework.web.bind.annotation.RestController
public class RestController {

    // add the @GetMapping annotation with the path “/hello” given as its parameter
    @GetMapping("/")
    // Create a method called helloCareerDevs(), that returns a String, and takes no parameters.
    private String helloCareerDevs() {
        // Implement code so the method returns the string “You requested the root route”
        return "You requested the root route";
    }

    // Create another get route of your own choosing
    @GetMapping("/login")
    private String login() {
        return "This is where you login";
    }

    // a GET request to the /random path will respond with a random number 1 through 100
    @GetMapping("/random")
    private int randomNumber() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        n += 1;
        return n;
    }

    // a GET request to the /joke path will respond with a joke. From there try making it pick a random joke from a list of stored jokes (Array of Strings?)
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

    // Try creating a route handler that can utilize a PathVariable
    @GetMapping("/joke/{id}")
    private String getJokeById(@PathVariable int id) {
        String[] jokes = {"How do you follow Will Smith in the snow? You follow the fresh prints.",
                "What do you call a fish wearing a bowtie? Sofishticated.",
                "I'm afraid for the calendar. Its days are numbered.",
                "Dear Math, grow up and solve your own problems."
        };
        return jokes[id];
    }

    // Try creating a route handler that can utilize a RequestParam
    @GetMapping("/jokes/")
    private String getJokeByRequestParam(@RequestParam int id) {
        String[] jokes = {"How do you follow Will Smith in the snow? You follow the fresh prints.",
                "What do you call a fish wearing a bowtie? Sofishticated.",
                "I'm afraid for the calendar. Its days are numbered.",
                "Dear Math, grow up and solve your own problems."
        };
        return jokes[id];
    }

    @GetMapping("/pic")
    private URL pic() throws MalformedURLException {
        URL imageURL = new URL("https://ashleyfurniture.scene7.com/is/image/AshleyFurniture/A600009156_1?$AFHS-PDP-Zoomed$");
        return imageURL;
    }

}
