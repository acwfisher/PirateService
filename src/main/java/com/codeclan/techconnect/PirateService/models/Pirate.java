package com.codeclan.techconnect.PirateService.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*; // JPA - Java Persistence API
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pirates")
public class Pirate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column
    private int age;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false) // Creating foreign key and making no pirate exist without a ship
    private Ship ship;

    @ManyToMany
    @JsonIgnoreProperties("pirates")
    @JoinTable(
            name = "pirates_raids",
            joinColumns = { @JoinColumn(
                    name = "pirate_id",
                    nullable = false,
                    updatable = false
            )},
            inverseJoinColumns = { @JoinColumn(
                    name = "raid_id",
                    nullable = false,
                    updatable = false
            )}
    )
    private List<Raid> raids;

    // Constructor
    public Pirate(String firstName, String lastName, int age, Ship ship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ship = ship;
        this.raids = new ArrayList<>();
    }

    // Default constructor
    public Pirate() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<Raid> getRaids() {
        return raids;
    }

    public void setRaids(List<Raid> raids) {
        this.raids = raids;
    }

    // Method to add raid to pirate
    public void addRaid(Raid raid) {
        this.raids.add(raid);
    }
}
