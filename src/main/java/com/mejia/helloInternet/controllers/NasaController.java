package com.mejia.helloInternet.controllers;

import com.mejia.helloInternet.models.NasaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

// // First step is to add the RestController and RequestMapping annotations to the NasaController class
@RestController
@RequestMapping("/nasa")
public class NasaController {

    @Autowired
    private Environment env;
    // Second step is to create a nasaApodEndpoint field within your new class.
    private final String myNasaKey = "fJmhvO9ivjLyd74qGI9eRnlKtbJ2P3mAPoMNElaG";
    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=" + myNasaKey;


    // Third step is to add a route handler to your code.
    @GetMapping("/apod")
    public ResponseEntity<?> apodHandler (RestTemplate restTemplate) {
        try {
            NasaModel response = restTemplate.getForObject(nasaApodEndpoint, NasaModel.class);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getUserById (RestTemplate restTemplate, @PathVariable String date) {
        try {

            // throws NumberFormatException if id is not an int
            Integer.parseInt(date);

            System.out.println("Getting user with date " + date);

            String url = nasaApodEndpoint + "/" + date;

            NasaModel response = restTemplate.getForObject(url, NasaModel.class);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("Date " + date + ", is not a valid date. Must be a date");

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("Photo Not Found With Date: " + date);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    // Fourth step is to make a request route that allows you to change the date of the APOD information being requested. You’ll need to use either @PathVariable or @RequestParam, either will work.
//    @GetMapping("/{date}")
//    private void getByDate(@RequestParam String date) {
//        return;
//    }
}
