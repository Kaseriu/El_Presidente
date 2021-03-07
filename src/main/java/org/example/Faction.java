package org.example;

public class Faction {

    private String name;
    private int numberOfPartisans;
    private int satisfactionPercentage;

    public Faction(){}

    public String getName() {
        return this.name;
    }

    public int getNumberOfPartisans() {
        return this.numberOfPartisans;
    }

    public int getSatisfactionPercentage() {
        return this.satisfactionPercentage;
    }

    public void setSatisfactionPercentage(int satisfactionPercentage) {
        this.satisfactionPercentage = satisfactionPercentage;
    }

    @Override
    public String toString() {
        return this.name + " - " +
                this.numberOfPartisans + " partisans - " +
                "Satisfaction : " + this.satisfactionPercentage +
                "%\n";
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
