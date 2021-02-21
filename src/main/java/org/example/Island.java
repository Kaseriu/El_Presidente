package org.example;

import org.example.enums.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {

    private String name;
    private int agriculturePercentage;
    private int industryPercentage;
    private int treasury;
    private int foodUnits;
    private String difficulty;
    private List<Faction> factions = new ArrayList<>();


    public Island(){}
    public Island(String name, int agriculturePercentage, int industryPercentage, int treasury, int foodUnits, String difficulty, List<Faction> factions) {

        this.name = name;
        this.agriculturePercentage = agriculturePercentage;
        this.industryPercentage = industryPercentage;
        this.treasury = treasury;
        this.foodUnits = foodUnits;
        this.difficulty = difficulty;
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

    public String getDifficulty() {
        return difficulty;
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

    public boolean cumulation(int agriculturePercentage, int industryPercentage){
        return this.agriculturePercentage + agriculturePercentage + this.agriculturePercentage + industryPercentage <= 100;
    }

    public void updateTreasuryComparedToIndustry(){
        this.treasury += this.industryPercentage*10;
    }

    public void updateFoodUnitsComparedToAgriculture(){
        this.foodUnits += this.agriculturePercentage*40;
    }
    public Faction getFactionByName(String name){
        Faction factiontmp = new Faction();
        for (Faction faction: this.factions) {
            if (faction.getName() == name){
                factiontmp = faction;
            }
        }
        return factiontmp;
    }

    public int getTotalPartisans(){
        int total = 0;
        for (Faction faction: this.factions) {
            total += faction.getNumberOfPartisans();
        }
        return total;
    }

    public void bribe(Faction faction){
        if(!faction.getName().equals("Loyalists")){
            this.treasury -= faction.getNumberOfPartisans()*15;
            Faction loyalist = getFactionByName("Loyalists");
            loyalist.updateSatisfaction(-(faction.getNumberOfPartisans()*15)/10);
            faction.setSatisfactionPercentage(faction.getSatisfactionPercentage() + 10);
        }
    }

    public void buyFoodUnits(int quantity){
        if(!(this.treasury < quantity*8)){
            this.treasury -= quantity*8;
            this.foodUnits += quantity;
        }
    }

    public void endOfYearResult(){
        int totalPartisans = getTotalPartisans();
        while(totalPartisans*4 > this.foodUnits){
            Random random = new Random();
            this.factions.get(random.nextInt(8)).deletePartisans();
        }
        this.foodUnits -= totalPartisans*4;
        if(foodUnits > 0){
            Random random = new Random();
            int randomPercentage = random.nextInt(10)+1;
            int newPartisans = 100*randomPercentage/totalPartisans;
            while (newPartisans>0){
                this.factions.get(random.nextInt(8)).updatePartisans(1);
                newPartisans--;
            }
        }
    }
    public boolean defeatCondition(){

        int totalPartisans = getTotalPartisans();
        int total = 0;

        for (Faction faction:this.factions) {
            total += faction.getNumberOfPartisans()*faction.getSatisfactionPercentage();
        }
        return total / totalPartisans >= percentageDefeat(this.difficulty);
    }
    public int percentageDefeat(String difficulty){
        if(difficulty.equals("NORMAL")){
            return 25;
        }
        if(difficulty.equals("EASY")){
            return 10;
        }
        return 50;
    }


}
