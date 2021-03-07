package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {

    private String name;
    private String story;
    private int agriculturePercentage;
    private int industryPercentage;
    private int treasury;
    private int foodUnits;
    private String difficulty;
    private List<Faction> factions = new ArrayList<>();
    private List<Events> events = new ArrayList<>();

    public Island(){}

    public String getName() {
        return this.name;
    }

    public String getStory() {
        return this.story;
    }

    public int getTreasury() {
        return this.treasury;
    }

    public int getFoodUnits() {
        return this.foodUnits;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public double getDifficulty() {

        if (this.difficulty.equals("1")) {
            return 0.5;
        }
        if (this.difficulty.equals("3")) {
            return 2;
        }

        return 1;
    }

    public List<Faction> getFactions() {
        return this.factions;
    }

    public void updateAgriculturePercentage(int agriculturePercentage) {
        this.agriculturePercentage += agriculturePercentage;
        if (this.agriculturePercentage <= 0) {
            this.agriculturePercentage = 0;
        }
    }

    public void updateIndustryPercentage(int industryPercentage) {
        this.industryPercentage += industryPercentage;
        if (this.industryPercentage <= 0) {
            this.industryPercentage = 0;
        }
    }

    public void updateTreasury(int treasury) {
        this.treasury += treasury;

        if (this.treasury < 0) {
            this.treasury = 0;
        }
    }

    public String displayIslandInformations() {
        String string = "";

        string += "Agriculture : " + this.agriculturePercentage + "%\n";
        string += "Industrie : " + this.industryPercentage + "%\n";
        string += "Trésorerie : " + this.treasury + "$\n";
        string += "Nourriture : " + this.foodUnits + " unités\n";

        for (Faction faction: this.factions
             ) {
            string += faction.toString();
        }

        return string;
    }

    public boolean cumulation(int agriculturePercentage, int industryPercentage){
        return this.agriculturePercentage + agriculturePercentage + this.industryPercentage + industryPercentage <= 100;
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
            if (faction.getName().equals(name)){
                factiontmp = faction;
            }
        }
        return factiontmp;
    }

    public List<Events> getEvents() {
        return events;
    }

    public int getTotalPartisans(){
        int total = 0;
        for (Faction faction: this.factions) {
            total += faction.getNumberOfPartisans();
        }
        return total;
    }

    public void bribe(Faction faction){
        if (this.treasury == 0 || this.treasury < faction.getNumberOfPartisans()*15) {
            System.out.println("Vous n'avez pas les fonds !");
        }
        else if(!faction.getName().equals("Loyalists")){
            updateTreasury(-faction.getNumberOfPartisans()*15);
            Faction loyalist = getFactionByName("Loyalists");
            loyalist.updateSatisfaction(-(faction.getNumberOfPartisans()*15)/10);
            faction.setSatisfactionPercentage(faction.getSatisfactionPercentage() + 10);
        }
    }

    public void buyFoodUnits(int quantity){
        if(quantity <= 0) {
            System.out.println("Valeur incorrect");
        }
        else if(!(this.treasury < quantity*8)){
            this.treasury -= quantity*8;
            this.foodUnits += quantity;
        }
        else {
            System.out.println("Vous n'avez pas les fonds suffisant !");
        }
    }

    public void endOfYearResult(){
        int totalPartisans = getTotalPartisans();
        while(totalPartisans*4 > this.foodUnits){
            Random random = new Random();
            this.factions.get(random.nextInt(8)).deletePartisans();
        }
        this.foodUnits -= totalPartisans*4;
        if(this.foodUnits > 0){
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

    public int displayFactionsForBribe(){
        int i = 1;
        for (Faction faction: this.factions) {

            if (!faction.getName().equals("Loyalists")) {
                System.out.println(i + " - " + faction.getName() + " - Satisfaction : "
                        + faction.getSatisfactionPercentage() + "% - Cout : "
                        + faction.getNumberOfPartisans()*15 + "$");;
            }
            i++;
        }
        System.out.println(i + " - " + "Retour");

        return i;
    }
}
