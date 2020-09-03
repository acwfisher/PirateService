package com.codeclan.techconnect.PirateService.controllers;

import com.codeclan.techconnect.PirateService.models.Pirate;
import com.codeclan.techconnect.PirateService.models.Raid;
import com.codeclan.techconnect.PirateService.repositories.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RaidController {
    @Autowired
    private RaidRepository raidRepository;

    // INDEX: get all raids
    @GetMapping(value = "/raids")
    public ResponseEntity<List<Raid>> getAllRaids() {
        List<Raid> foundRaids = raidRepository.findAll();
        return new ResponseEntity<List<Raid>>(foundRaids, HttpStatus.OK);
    }

    // SHOW: show details about a specific raid
    // e.g. http://localhost:8080/raids/1
    @GetMapping(value = "/raids/{id}")
    public ResponseEntity getRaid(@PathVariable Long id) {
        Optional<Raid> foundRaid = raidRepository.findById(id);

        if (foundRaid.isPresent()) {
            return new ResponseEntity(foundRaid, HttpStatus.OK);
        }
        else {
            return new ResponseEntity(foundRaid, HttpStatus.NOT_FOUND);
        }
    }

    // CREATE: add a new raid
    @PostMapping(value = "/raids")
    public ResponseEntity<Raid> postRaid(@RequestBody Raid raid) {
        raidRepository.save(raid);
        return new ResponseEntity<>(raid, HttpStatus.CREATED);
    }

}
