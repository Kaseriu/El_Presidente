package org.example;

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
        return this.scenarios;
    }

    public int getSeasons() {
        return this.seasons;
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


        this.island = JsonReader.setupIslandFromJson(this.scenarios.get(choice));
    }

    public void displayChosenScenario() {

        System.out.println(this.island.getName());
        System.out.println(this.island.getStory());
    }

    public void gameStart() {

        Scanner scanner = new Scanner(System.in);
        String input = "0";
        Events event;
        Choices selectedChoice;
        List<Choices> choices;
        List<Faction> factions;
        int i = 1;

        System.out.println("Saison " + this.seasons);
        event = getRandomEvent();
        System.out.println(event.getName());
        choices = event.getChoices();

        while (Integer.parseInt(input) > choices.size() || Integer.parseInt(input) <= 0) {
            for (Choices choice: choices) {

                System.out.println(i + " - " + choice.getChoice());
                i++;
            }

            input = scanner.nextLine();

            if (!App.isStringInteger(input, 10)) {
                    System.out.println("Taper un chiffre");
                    input = "0";
            }
            else if(Integer.parseInt(input) > choices.size() || Integer.parseInt(input) <= 0){
                System.out.println("Entrez un choix valide !");
                i = 1;
            }
            else {
                selectedChoice = choices.get(Integer.parseInt(input) - 1);
                updateFromChoice(this.island, selectedChoice.getEffects());
                island.defeatCondition();
            }

            if (seasons >= 4) {

                while (!input.equals("3")) {

                    System.out.println("Fin d'année choisissez un évènement");
                    System.out.println("1 - Pot de vin");
                    System.out.println("2 - Marché alimentaire");
                    System.out.println("3 - Continuer");

                    input = scanner.nextLine();

                    if (input.equals("1")) {

                        i = 1;
                        System.out.println("Choisissez la faction à soudoyer");
                        factions = island.getFactions();
                        for (Faction faction: factions) {

                            System.out.println(i + " - " + faction.getName());
                            i++;
                        }
                        input = scanner.nextLine();
                        island.bribe(factions.get(Integer.parseInt(input) - 1));
                    }

                    if (input.equals("2")) {

                        System.out.println("Choissisez la quantité de nourriture à acheter (8$ par unité)");
                        input = scanner.nextLine();
                        island.buyFoodUnits(Integer.parseInt(input));
                    }
                }

                seasons = 1;
            }
            else {
                seasons++;
            }
        }
    }

    public Events getRandomEvent() {

        Random random = new Random();
        List<Events> events = this.island.getEvents();


        return events.get(random.nextInt(events.size()));
    }

    public void updateFromChoice(Island island, List<Effect> effects) {

        List<Faction> factions = island.getFactions();

        updateFactionFromChoice(factions, effects);
        updateFactorFromChoice(island, effects);
    }

    public void updateFactionFromChoice(List<Faction> factions, List<Effect> effects) {

        HashMap<String, Integer> actionOnFaction;

        for (Faction faction: factions) {
            for (Effect effect: effects) {
                actionOnFaction = effect.getActionOnFaction();
                for (String i : actionOnFaction.keySet()) {
                    if (i.toLowerCase().equals(faction.getName().toLowerCase())) {
                        faction.updateSatisfaction(actionOnFaction.get(i));
                    }
                }
            }
        }
    }

    public void updateFactorFromChoice(Island island, List<Effect> effects) {

        HashMap<String, Integer> actionOnFactor;

        for (Effect effect: effects) {
            actionOnFactor = effect.getActionOnFactor();
            for (String i : actionOnFactor.keySet()) {
                switch (i) {

                    case "AGRICULTURE":
                        island.updateAgriculturePercentage(actionOnFactor.get(i));
                        break;
                    case "INDUSTRY":
                        island.updateIndustryPercentage(actionOnFactor.get(i));
                        break;
                    case "TREASURY":
                        island.updateTreasury(actionOnFactor.get(i));
                        break;
                }
            }
        }
    }
}
