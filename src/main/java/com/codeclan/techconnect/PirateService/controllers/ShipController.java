package com.codeclan.techconnect.PirateService.controllers;

import com.codeclan.techconnect.PirateService.models.Pirate;
import com.codeclan.techconnect.PirateService.models.Ship;
import com.codeclan.techconnect.PirateService.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShipController {
    @Autowired
    private ShipRepository shipRepository;

    // INDEX: get all ships
    @GetMapping(value = "/ships")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> foundShips = shipRepository.findAll();
        return new ResponseEntity<List<Ship>>(foundShips, HttpStatus.OK);
    }

    // SHOW: show details about a specific ship
    // e.g. http://localhost:8080/ships/1
    @GetMapping(value = "/ships/{id}")
    public ResponseEntity<Optional<Ship>> getShip(@PathVariable Long id) {
        Optional<Ship> foundShip = shipRepository.findById(id);

        if (foundShip.isPresent()) {
            return new ResponseEntity<>(foundShip, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(foundShip, HttpStatus.NOT_FOUND);
        }
    }

    // CREATE: add a new ship
    @PostMapping(value = "/ships")
    public ResponseEntity<Ship> postShip(@RequestBody Ship ship) {
        shipRepository.save(ship);
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }

}
