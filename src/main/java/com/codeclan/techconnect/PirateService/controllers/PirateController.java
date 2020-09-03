package com.codeclan.techconnect.PirateService.controllers;

import com.codeclan.techconnect.PirateService.models.Pirate;
import com.codeclan.techconnect.PirateService.repositories.PirateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PirateController {
    @Autowired
    private PirateRepository pirateRepository;

    // INDEX: get all pirates
    @GetMapping(value = "/pirates")
    public ResponseEntity<List<Pirate>> getAllPirates() {
        List<Pirate> foundPirates = pirateRepository.findAll();
        return new ResponseEntity<List<Pirate>>(foundPirates, HttpStatus.OK);
    }

    // SHOW: show details about a specific pirate
    // e.g. http://localhost:8080/pirates/1
    @GetMapping(value = "/pirates/{id}")
    public ResponseEntity getPirate(@PathVariable Long id) {
        Optional<Pirate> foundPirate = pirateRepository.findById(id);

        if (foundPirate.isPresent()) {
            return new ResponseEntity(foundPirate, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(foundPirate, HttpStatus.NOT_FOUND);
        }
    }

    // CREATE: add a new pirate
    @PostMapping(value = "/pirates")
    public ResponseEntity<Pirate> postPirate(@RequestBody Pirate pirate) {
        pirateRepository.save(pirate);
        return new ResponseEntity<>(pirate, HttpStatus.CREATED);
    }

}
