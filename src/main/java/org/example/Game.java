package org.example;

import java.util.HashMap;

public class Game {

    private HashMap<String, String> scenarios = new HashMap<String, String>();
    private int seasons;

    public Game() {

        this.seasons = 1;
        this.scenarios.put("Attack On Titans", "src/main/java/org/jsonFile/attackOnTitans.json");
        this.scenarios.put("Cold War USA", "src/main/java/org/jsonFile/coldWarUSA.json");
        this.scenarios.put("Cold War USSR", "src/main/java/org/jsonFile/coldWarUSSR.json");
    }

    public HashMap<String, String> getScenarios() {
        return scenarios;
    }

    public int getSeasons() {
        return seasons;
    }

    public String[][] displayScenarios() {

        String[][] chaine = new String[3][3];
        int i = 0;

        for (String scenarioName : this.scenarios.keySet()) {
            chaine[i][0] = i + 1 + " - ";
            chaine[i][1] = scenarioName;
            chaine[i][2] = " | ";
            i++;
        }

        return chaine;
    }

    public void launchScenario(String choice) {


        JsonReader.setupIslandFromJson(scenarios.get(choice));
    }

//    public void displayScenario
}
