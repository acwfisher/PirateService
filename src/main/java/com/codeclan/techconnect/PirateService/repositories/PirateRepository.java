package com.codeclan.techconnect.PirateService.repositories;

import com.codeclan.techconnect.PirateService.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // makes this a singleton that's created on startup
public interface PirateRepository extends JpaRepository<Pirate, Long> {

//    List<Pirate> findByName(String name);
//
//    List<Pirate> findByAge(int age);
//
//    Pirate findDistinctByName(String name); // Would bring back only the first pirate if there were more than one
//
//    List<Person> findDistinctByLastNameOrFirstName(String lastName, String firstName);

    List<Pirate> findByAgeGreaterThan(int age);

    // Get all the pirates that went on a particular raid
    // Action to perform: find (approx. equiv. to get)
    // (optionally): noun: type of object to return
    // property to find by(in this case, an object)
    // property on the object to find on (id)
    List<Pirate> findByRaidsId(long id);


}
