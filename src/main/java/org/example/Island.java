package org.example;

import java.util.ArrayList;
import java.util.List;

public class Island {

    private String name;
    private int agriculturePercentage;
    private int industryPercentage;
    private int treasury;
    private int foodUnits;
    private List<Faction> factions = new ArrayList<>();

    public Island(){}
    public Island(String name, int agriculturePercentage, int industryPercentage, int treasury, int foodUnits, List<Faction> factions) {

        this.name = name;
        this.agriculturePercentage = agriculturePercentage;
        this.industryPercentage = industryPercentage;
        this.treasury = treasury;
        this.foodUnits = foodUnits;
        this.factions = factions;
    }

    public String getName() {
        return name;
    }

    public int getAgriculturePercentage() {
        return agriculturePercentage;
    }

    public int getIndustryPercentage() {
        return industryPercentage;
    }

    public int getTreasury() {
        return treasury;
    }

    public int getFoodUnits() {
        return foodUnits;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    @Override
    public String toString() {
        String chaine =  "Island{" +
                "name='" + name + '\'' +
                ", agriculture=" + agriculturePercentage +
                ", industry=" + industryPercentage +
                ", treasury=" + treasury +
                ", foodUnits=" + foodUnits +
                '}';
        for (Faction faction: factions
             ) {
            chaine += faction.toString();
        }
        return chaine;
    }
}
