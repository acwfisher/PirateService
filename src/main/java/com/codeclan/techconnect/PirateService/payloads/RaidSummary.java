package com.codeclan.techconnect.PirateService.payloads;

import java.util.ArrayList;

public class RaidSummary {

    private String raidLocation;
    private int raidLoot;
    private int pirateCount;
    private ArrayList<String> shipNames;

    public RaidSummary() {

    }

    public String getRaidLocation() {
        return raidLocation;
    }

    public void setRaidLocation(String raidLocation) {
        this.raidLocation = raidLocation;
    }

    public int getRaidLoot() {
        return raidLoot;
    }

    public void setRaidLoot(int raidLoot) {
        this.raidLoot = raidLoot;
    }

    public int getPirateCount() {
        return pirateCount;
    }

    public void setPirateCount(int pirateCount) {
        this.pirateCount = pirateCount;
    }

    public ArrayList<String> getShipNames() {
        return shipNames;
    }

    public void setShipNames(ArrayList<String> shipNames) {
        this.shipNames = shipNames;
    }
}
