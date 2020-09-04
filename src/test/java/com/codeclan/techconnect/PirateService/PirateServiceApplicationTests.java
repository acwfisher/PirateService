package com.codeclan.techconnect.PirateService;

import com.codeclan.techconnect.PirateService.models.Pirate;
import com.codeclan.techconnect.PirateService.models.Raid;
import com.codeclan.techconnect.PirateService.models.Ship;
import com.codeclan.techconnect.PirateService.repositories.PirateRepository;
import com.codeclan.techconnect.PirateService.repositories.RaidRepository;
import com.codeclan.techconnect.PirateService.repositories.ShipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PirateServiceApplicationTests {

	@Autowired // Allows us to dependency "inject" instances wherever we want
	private PirateRepository pirateRepository;
	@Autowired
	private ShipRepository shipRepository;
	@Autowired
	private RaidRepository raidRepository;
	// Because we've used autowired annotation, we don't need to new up these repositories

	@Test
	void contextLoads() {
	}

	@Test
	public void canCreatePirateAndShipAndSave() {
		Ship ship = new Ship("The Black Pearl");
		shipRepository.save(ship);

		Pirate pirate = new Pirate("Jack", "Sparrow", 33, ship);
		pirateRepository.save(pirate);
		// take instances of ShipRepository and PirateRepository and use that to save the ship and pirate to the DB
	}

	@Test
	public void addPiratesAndRaids() {
		Ship ship = new Ship("The Black Pearl");
		shipRepository.save(ship);

		Pirate pirate = new Pirate("Jack", "Sparrow", 33, ship);
		pirateRepository.save(pirate);

		Raid tortugaRaid = new Raid("Tortuga", 999);
		raidRepository.save(tortugaRaid); // Going to give our raid an ID

		tortugaRaid.addPirate(pirate);
		raidRepository.save(tortugaRaid); // Save new row in join table
	}

	@Test
	public void canFindPiratesOver30() {
		List<Pirate> foundPirates = pirateRepository.findByAgeGreaterThan(30);
	}

	@Test
	public void canFindBarbadosRaids() {
		List<Raid> foundRaids = raidRepository.findByLocation("Barbados");
//		assertEquals("Barbados", foundRaids.get(1).getLocation());
	}

	@Test
	public void canGetPiratesByGivenRaid() {
		List<Pirate> foundPirates = pirateRepository.findByRaidsId(1L); // L here means treat this primitive as a Long
		assertEquals(1, foundPirates.size());
		assertEquals("Jack", foundPirates.get(0).getFirstName());
	}

	@Test
	public void canGetShipsByPiratesFirstNameJack() {
		List<Ship> foundShips = shipRepository.findByPiratesFirstName("Jack");
		assertEquals(1, foundShips.size());
		assertEquals("The Black Pearl", foundShips.get(0).getName());
	}

	@Test
	public void canGetRaidsByGivenShip() {
		List<Raid> foundRaids = raidRepository.findByPiratesShipId(3L);
//		assertEquals("The Flying Dutchman", foundRaids.get(0).);
	}

}
