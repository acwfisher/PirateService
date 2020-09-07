package com.codeclan.techconnect.PirateService.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ships")
public class Ship {

    // Properties, including primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // One ship has many pirates
    @OneToMany(mappedBy = "ship")
    @JsonIgnoreProperties("ship") // Prevents infinite recursion - stack overflow
    private List<Pirate> pirates;

    // Constructor
    public Ship(String name) {
        this.name = name;
        this.pirates = new ArrayList<>();
    }

    // Default constructor
    public Ship() {
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(List<Pirate> pirates) {
        this.pirates = pirates;
    }
}
