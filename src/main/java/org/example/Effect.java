package org.example;

import java.util.HashMap;

public class Effect {

    private HashMap<String, Integer> actionOnFaction = new HashMap<String, Integer>();
    private HashMap<String, Integer> actionOnFactor = new HashMap<String, Integer>();
    private int partisants;

    public Effect() {
    }

    @Override
    public String toString() {
        String chaine =  "Effect{" + "actionOnFaction=";

        for (String i : this.actionOnFaction.keySet()) {
            chaine += "Faction: " + i + " Chiffre: " + this.actionOnFaction.get(i);
        }

        chaine += "actionOnFactor=";
        for (String i : this.actionOnFactor.keySet()) {
            chaine += "Faction: " + i + " Chiffre: " + this.actionOnFactor.get(i) + " ";
        }

        chaine += "partisants='" + this.partisants;

        chaine += '}';

        return chaine;
    }

    public HashMap getActionOnFaction() {
        return actionOnFaction;
    }

    public HashMap getActionOnFactor() {
        return actionOnFactor;
    }

    public int getPartisants() {
        return partisants;
    }
}
