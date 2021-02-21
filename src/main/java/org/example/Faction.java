package org.example;

public class Faction {

    private String name;
    private int numberOfPartisans;
    private int satisfactionPercentage;

    public Faction(){}

    public String getName() {
        return name;
    }

    public int getNumberOfPartisans() {
        return numberOfPartisans;
    }

    public int getSatisfactionPercentage() {
        return satisfactionPercentage;
    }

    public void setSatisfactionPercentage(int satisfactionPercentage) {
        this.satisfactionPercentage = satisfactionPercentage;
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
        if (this.satisfactionPercentage > 0){
            this.satisfactionPercentage += satisfactionPercentage;
            if(this.satisfactionPercentage < 0){
                this.satisfactionPercentage = 0;
            }
            if(this.satisfactionPercentage > 100){
                this.satisfactionPercentage = 100;
            }
        }
    }

    public void updatePartisans(int numberOfPartisans){
        this.numberOfPartisans += numberOfPartisans;
        if(this.numberOfPartisans < 0){
            this.numberOfPartisans = 0;
        }
    }

    public void deletePartisans(){
        updatePartisans(-1);
        updateSatisfaction(-2);
    }


}
