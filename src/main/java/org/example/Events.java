package org.example;

import java.util.ArrayList;
import java.util.List;

public class Events {

    private String name;
    private List<Choices> choices = new ArrayList<Choices>();

    public Events() {
    }

    @Override
    public String toString() {
        String chaine =  "Event{" + "name='" + this.name + '\'' + '}';

        for (Choices choice: this.choices
        ) {
            chaine += choice.toString();
        }

        return chaine;
    }

    public String getName() {
        return name;
    }

    public List<Choices> getChoices() {
        return choices;
    }
}
