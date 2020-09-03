package com.codeclan.techconnect.PirateService.repositories;

import com.codeclan.techconnect.PirateService.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // makes this a singleton that's created on startup
public interface PirateRepository extends JpaRepository<Pirate, Long> {
}
