package com.mejia.helloInternet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// // First step is to add the RestController and RequestMapping annotations to the NasaController class
@RestController
@RequestMapping("/nasa")
public class NasaController {
    // Second step is to create a nasaApodEndpoint field within your new class.
    private final String myNasaKey = "fJmhvO9ivjLyd74qGI9eRnlKtbJ2P3mAPoMNElaG";
    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=" + myNasaKey;

    // Third step is to add a route handler to your code.
    @GetMapping("/apod")
    public Object apodHandler (RestTemplate restTemplate) {
        return restTemplate.getForObject(nasaApodEndpoint, Object.class);
    }

    // Fourth step is to make a request route that allows you to change the date of the APOD information being requested. You’ll need to use either @PathVariable or @RequestParam, either will work.
    @GetMapping("/{date}")
    private void getByDate(@RequestParam String date) {
        return;
    }
}
