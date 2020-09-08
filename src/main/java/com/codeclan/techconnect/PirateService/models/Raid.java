package com.codeclan.techconnect.PirateService.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "raids")
public class Raid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="location")
    private String location;

    @Column(name="loot")
    private int loot;

    @ManyToMany
    @JsonIgnoreProperties("raids")
    @JoinTable(
            name = "pirates_raids",
            joinColumns = { @JoinColumn(
                    name = "raid_id",
                    nullable = false,
                    updatable = false
            )},
            inverseJoinColumns = { @JoinColumn( // This is the column we want to correspond to the above column's "raid_id"
                    name = "pirate_id",
                    nullable = false,
                    updatable = false
            )}
    )
    private List<Pirate> pirates;

    // Constructor
    public Raid(String location, int loot) {
        this.location = location;
        this.loot = loot;
        this.pirates = new ArrayList<>();
    }

    // Default constructor
    public Raid() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    public List<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(List<Pirate> pirates) {
        this.pirates = pirates;
    }

    // Method to add pirate to raid
    public void addPirate(Pirate pirateToAdd) {
        this.pirates.add(pirateToAdd);
    }
}
