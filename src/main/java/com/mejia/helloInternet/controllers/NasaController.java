package com.mejia.helloInternet.controllers;

import com.mejia.helloInternet.models.NasaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/api/nasa")
public class NasaController {

    /*
    Option 1 for accessing env vars from app.props
    @Autowired
    private Environment env;
     */


//    Option 2 for accessing env vars from app.props
    @Value("${NASA_KEY}")
    private String apiKey;

    @GetMapping("/testKey")
    private String getApiKey () {
        return apiKey;
    }

    private final String nasaApodEndpoint = "https://api.nasa.gov/planetary/apod?api_key=";
    @GetMapping("/apod")
    public ResponseEntity<?> apodHandler (RestTemplate restTemplate) {
        try {
//            String key = env.getProperty("NASA_KEY");

            String url = nasaApodEndpoint + apiKey;
            NasaModel response = restTemplate.getForObject(url, NasaModel.class);

            return ResponseEntity.ok(response);

        } catch (HttpClientErrorException.Forbidden e) {
            return ResponseEntity.status(500).body("Server has no API key or API key is invalid");

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getApodByDatePV (RestTemplate restTemplate, @PathVariable String date) {
        try {
//            String key = env.getProperty("NASA_KEY");

            System.out.println("Getting user with date " + date);

            String url = nasaApodEndpoint + apiKey + "&date=" + date;

            NasaModel response = restTemplate.getForObject(url, NasaModel.class);

            return ResponseEntity.ok(response);

        } catch (HttpClientErrorException.BadRequest e) {
            String rawErr = e.getMessage() != null ? e.getMessage() : "";
            String apiErrMsg = extractNasaErrorMsg(rawErr);

            return ResponseEntity.badRequest().body("Invalid Date Provided: " + date + "\n" + apiErrMsg);


        } catch (HttpClientErrorException.Forbidden e) {
            return ResponseEntity.status(500).body("Server has no API key or API key is invalid");

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("Photo Not Found With Date: " + date);

        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("Date " + date + ", is not a valid date. Must be a date");

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/bydate")
    public ResponseEntity<?> getApodByDateRP (RestTemplate restTemplate, @RequestParam () String date) {
        try {
//            String key = env.getProperty("NASA_KEY");

            System.out.println("Getting user with date " + date);

            String url = nasaApodEndpoint + apiKey + "&date=" + date;

            NasaModel response = restTemplate.getForObject(url, NasaModel.class);

            return ResponseEntity.ok(response);

        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("Date " + date + ", is not a valid date. Must be a date");

        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(404).body("Photo Not Found With Date: " + date);

        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.status(400).body("Date Provided Is Not Valid: " + date);

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private String extractNasaErrorMsg (String fullErrMsg) {
        String[] splitErrMsg = fullErrMsg.split("\"");
        for (int i = 0; i < splitErrMsg.length; i++) {
            if (splitErrMsg[i].equals("msg") && i+2 < splitErrMsg.length) {
                return splitErrMsg[i+2];
            }
        }
        return "Error: no more info available";
    }
}
