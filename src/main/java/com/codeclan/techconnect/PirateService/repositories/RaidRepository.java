package com.codeclan.techconnect.PirateService.repositories;

import com.codeclan.techconnect.PirateService.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaidRepository extends JpaRepository<Raid, Long> {

    // TODO: Find Raid by Location

    List<Raid> findByLocation(String location);

    List<Raid> findByPiratesShipId(long id);
}
