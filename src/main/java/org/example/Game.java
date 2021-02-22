package org.example;

import org.example.enums.Season;

import java.util.*;

public class Game {

    private HashMap<String, String> scenarios = new HashMap<String, String>();
    private int seasons;
    private Island island;

    public Game() {

        this.seasons = 1;
        this.scenarios.put("Attack On Titans", "src/main/java/org/jsonFile/attackOnTitans.json");
        this.scenarios.put("Cold War USA", "src/main/java/org/jsonFile/coldWarUSA.json");
        this.scenarios.put("Cold War USSR", "src/main/java/org/jsonFile/coldWarUSSR.json");
        this.scenarios.put("Bac à sable", "src/main/java/org/jsonFile/sandbox.json");
    }

    public HashMap<String, String> getScenarios() {
        return scenarios;
    }

    public int getSeasons() {
        return seasons;
    }

    public String[][] displayScenarios() {

        String[][] chaine = new String[4][3];
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


        this.island = JsonReader.setupIslandFromJson(scenarios.get(choice));
    }

    public void displayChosenScenario() {

        System.out.println(this.island.getName());
        System.out.println(this.island.getStory());
    }

    public void gameStart() {

        Scanner scanner = new Scanner(System.in);
        String input;
        Events event;
        List<Choices> choices = new ArrayList<>();
        int i = 1;

        System.out.println("Saison " + this.seasons);
        event = getRandomEvent();
        System.out.println(event.getName());
        choices = event.getChoices();
        for (Choices choice: choices) {

            System.out.println(i + " - " + choice.getChoice());
            i++;
        }

        input = scanner.nextLine();
    }

    public Events getRandomEvent() {

        Random random = new Random();
        List<Events> events = this.island.getEvents();


        return events.get(random.nextInt(events.size()));
    }
}
