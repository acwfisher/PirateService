package com.codeclan.techconnect.PirateService;

import com.codeclan.techconnect.PirateService.models.Pirate;
import com.codeclan.techconnect.PirateService.models.Ship;
import com.codeclan.techconnect.PirateService.repositories.PirateRepository;
import com.codeclan.techconnect.PirateService.repositories.ShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {PirateServiceApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
        )
public class PirateControllerTests {
    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    PirateRepository pirateRepository;
    @Autowired
    ShipRepository shipRepository;

    @Test
    public void canGetAllPiratesRoute() {
        // 1. Use restTemplate to make a GET request to /pirates
        ResponseEntity<Pirate[]> response = restTemplate.getForEntity("/pirates/", Pirate[].class);
        // 2. Take the response from this and store it in a variable of type Pirate[]
        Pirate[] pirates = response.getBody();
        // 3. Assert that the first pirate that comes back has firstName of Jack
        assertEquals("Jack", pirates[0].getFirstName());
    }

    @Test
    public void canPostPirate() {
        // Get a Ship object that exists in DB
        Ship ship = shipRepository.findById(1L).get();
        // Create a new Pirate object (no ID - doesn't exist in DB)
        Pirate pirate = new Pirate("Patti", "Smith", 65, ship);
        // Create an HttpEntity object (payload)
        HttpEntity<Pirate>requestPayload = new HttpEntity<>(pirate);
        // POST the entity to "/pirates/" - this will return a response
        ResponseEntity<Pirate> response = restTemplate.postForEntity("/pirates/", requestPayload, Pirate.class);
        // assert that response has status code 201 (created)
        assertEquals(201, response.getStatusCodeValue());
        // assert that the pirate exists in DB
        Long pirateId = response.getBody().getId();
        Pirate foundPirate = pirateRepository.findById(pirateId).get();
        assertEquals("Patti", foundPirate.getFirstName());
    }
}
