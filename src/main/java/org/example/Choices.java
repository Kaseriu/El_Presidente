package org.example;

import java.util.ArrayList;
import java.util.List;

public class Choices {

    private String choice;
    private List<Effect> effects = new ArrayList<Effect>();

    public Choices() {
    }

    @Override
    public String toString() {
        String chaine =  "Choices{" + "choice='" + this.choice + '\'' + '}';

        for (Effect effect: this.effects
        ) {
            chaine += effect.toString();
        }

        return chaine;
    }

    public String getChoice() {
        return this.choice;
    }

    public List<Effect> getEffects() {
        return this.effects;
    }
}
