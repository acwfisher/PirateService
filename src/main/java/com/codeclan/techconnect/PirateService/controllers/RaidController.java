package com.codeclan.techconnect.PirateService.controllers;

import com.codeclan.techconnect.PirateService.models.Pirate;
import com.codeclan.techconnect.PirateService.models.Raid;
import com.codeclan.techconnect.PirateService.payloads.RaidSummary;
import com.codeclan.techconnect.PirateService.repositories.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<Optional<Raid>> getRaid(@PathVariable Long id) {
        Optional<Raid> foundRaid = raidRepository.findById(id);

        if (foundRaid.isPresent()) {
            return new ResponseEntity<>(foundRaid, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(foundRaid, HttpStatus.NOT_FOUND);
        }
    }

    // SHOW: specific detail about specific raid
    @GetMapping(value = "/raids/{id}/location")
    public ResponseEntity<String> getLocationFromRaid(@PathVariable Long id) {
        // First get the raid
        Raid foundRaid = raidRepository.findById(id).get();
        // then get the location from the raid
        String raidLocation = foundRaid.getLocation();
        // return response entity with String body
        return new ResponseEntity<>(raidLocation, HttpStatus.OK);
    }

    @GetMapping(value = "/raids/{id}/summary")
    public ResponseEntity<RaidSummary> getRaidSummary(@PathVariable Long id) {
        // Get raid info from db
        Raid raid = raidRepository.getOne(id);
        // Create raidSummary object
        RaidSummary raidSummary = new RaidSummary();
        // Set raidSummary properties based on raid details:
        raidSummary.setRaidLocation(raid.getLocation()); // location
        raidSummary.setRaidLoot(raid.getLoot()); // loot
        raidSummary.setPirateCount(raid.getPirates().size()); // number of pirates

        ArrayList<String> shipNames = new ArrayList<>();
        for(Pirate pirate : raid.getPirates()) {
            String shipName = pirate.getShip().getName();
            if (!shipNames.contains(shipName)) {
                shipNames.add(shipName);
            }
        }
        raidSummary.setShipNames(shipNames); // ship names

        return new ResponseEntity<>(raidSummary, HttpStatus.OK);
    }

    // CREATE: add a new raid
    @PostMapping(value = "/raids")
    public ResponseEntity<Raid> postRaid(@RequestBody Raid raid) {
        raidRepository.save(raid);
        return new ResponseEntity<>(raid, HttpStatus.CREATED);
    }

}
