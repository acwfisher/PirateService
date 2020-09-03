package com.codeclan.techconnect.PirateService.repositories;

import com.codeclan.techconnect.PirateService.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaidRepository extends JpaRepository<Raid, Long> {
}
