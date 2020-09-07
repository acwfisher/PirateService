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
    // ... Handle GET /pirates
    // ... Handle GET /pirates?overAge=30
    // ... Handle GET /pirates?raidId=2
    @GetMapping(value = "/pirates")
    public ResponseEntity<List<Pirate>> getAllPiratesAndFilterAge(
            @RequestParam(name = "overAge", required = false) Integer age, // Integer in order to do null check
            @RequestParam(name = "raidId", required = false) Long raidId
    ) {
        // If query String value overAge is not null then call the findByAgeGreaterThan method:
        if(age != null) { // primitives cannot be null, hence why Integer object instead of int
            return new ResponseEntity<>(pirateRepository.findByAgeGreaterThan(age), HttpStatus.OK);
        }
        // If query String value raidId is not null then call the findByRaidsId method:
        if(raidId != null) {
            return new ResponseEntity<>(pirateRepository.findByRaidsId(raidId), HttpStatus.OK);
        }
        // otherwise return all the pirates:
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
