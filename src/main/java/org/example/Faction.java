package org.example;

public class Faction {

    private String name;
    private int numberOfPartisans;
    private int satisfactionPercentage;

    public Faction(){}
    public Faction(String name, int numberOfPartisans, int satisfactionPercentage) {

        this.name = name;
        this.numberOfPartisans = numberOfPartisans;
        this.satisfactionPercentage = satisfactionPercentage;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPartisans() {
        return numberOfPartisans;
    }

    public int getSatisfactionPercentage() {
        return satisfactionPercentage;
    }

    @Override
    public String toString() {
        return "Faction{" +
                "name='" + name + '\'' +
                ", numberOfPartisans=" + numberOfPartisans +
                ", satisfactionPercentage=" + satisfactionPercentage +
                '}';
    }

    public void updateSatisfaction(int satisfactionPercentage){


    }
}
