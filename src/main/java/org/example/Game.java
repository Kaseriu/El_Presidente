package org.example;

import java.util.*;

public class Game {

    private HashMap<String, String> scenarios = new HashMap<String, String>();
    private int seasons;
    private Island island;

    public Game() {

        this.seasons = 1;
        this.scenarios.put("Attack On Titans", "json/attackOnTitans.json");
        this.scenarios.put("Cold War USA", "json/coldWarUSA.json");
        this.scenarios.put("Cold War USSR", "json/coldWarUSSR.json");
        this.scenarios.put("Bac à sable", "json/sandbox.json");

    }

    public Island getIsland() {
        return island;
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
        String input;
        Events event;
        Events previousEvent = null;
        Choices selectedChoice;
        List<Choices> choices;
        List<Faction> factions;
        int i = 1;
        boolean endGame = false;
        boolean validChoice;
        boolean exitCondition;


        while (!endGame) {

            event = getRandomEvent(previousEvent);

            choices = event.getChoices();
            input = "0";

            while (Integer.parseInt(input) > choices.size() || Integer.parseInt(input) <= 0) {

                displaySeason();
                System.out.println(event.getName());
                validChoice = false;

                while (!validChoice) {

                    for (Choices choice: choices) {
                        System.out.println(i + " - " + choice.getChoice());
                        i++;
                    }

                    input = scanner.nextLine();

                    if (!App.isStringInteger(input, 10)) {
                        System.out.println("Taper un chiffre");
                        i = 1;
                    }
                    else if(Integer.parseInt(input) > choices.size() || Integer.parseInt(input) <= 0){
                        System.out.println("Entrez un choix valide !");
                        i = 1;
                    }
                    else {
                        selectedChoice = choices.get(Integer.parseInt(input) - 1);
                        updateFromChoice(this.island, selectedChoice.getEffects());

                        if (!this.island.defeatCondition()) {

                            endGame = true;
                            System.out.println("Vous avez moins de 50% de satisfaction générale, vous êtes renversé par un coup d'État !");
                        }

                        System.out.println(this.island.displayIslandInformations());
                        validChoice = true;
                    }
                }
                i = 1;
            }

            if (this.seasons >= 4 && !endGame) {

                while (!input.equals("3")) {

                    System.out.println("Fin d'année choisissez un évènement");
                    System.out.println("1 - Pot de vin");
                    System.out.println("2 - Marché alimentaire");
                    System.out.println("3 - Continuer");

                    input = scanner.nextLine();

                    if (input.equals("1")) {

                        exitCondition = false;
                        factions = this.island.getFactions();
                        System.out.println("Choisissez la faction à soudoyer (+10% de satisfaction, baisse la satisfaction des Loyalists)");

                        while (!exitCondition) {

                            System.out.println("Fonds disponible : " + island.getTreasury() + "$");
                            this.island.displayFactionsForBribe();

                            input = scanner.nextLine();

                            if (App.isStringInteger(input, 10) && Integer.parseInt(input) < factions.size() - 1
                                    && Integer.parseInt(input) > 0) {
                                this.island.bribe(factions.get(Integer.parseInt(input) - 1));
                                System.out.println(this.island.displayIslandInformations());
                                exitCondition = true;
                            }
                            if (App.isStringInteger(input, 10) && Integer.parseInt(input) > factions.size() - 1
                                    && Integer.parseInt(input) > 0 && Integer.parseInt(input) == i) {

                                exitCondition = true;
                            }
                            else {

                                System.out.println("Choisissez un numéro de faction valable");
                            }
                        }
                    }

                    if (input.equals("2")) {

                        exitCondition = false;
                        int funds = island.getTreasury();

                        while (!exitCondition) {

                            System.out.println("Choisissez la quantité de nourriture à acheter (8$ par unité)");
                            System.out.println("Fonds disponible : " + this.island.getTreasury() + "$");
                            System.out.println("Nourriture disponible : " + this.island.getFoodUnits() + " unités");
                            System.out.println("Population totale : " + this.island.getTotalPartisans() + " partisans");
                            System.out.println("Nourriture nécessaire pour nourrir toute votre population : "
                                    + this.island.getTotalPartisans() / 4 + " unités");
                            System.out.println("Taper 0 pour retourner au menu précédent");
                            input = scanner.nextLine();

                            if (App.isStringInteger(input, 10) && Integer.parseInt(input) > 0) {

                                this.island.buyFoodUnits(Integer.parseInt(input));
                                System.out.println(this.island.displayIslandInformations());

                                if (funds != this.island.getTreasury()) {
                                    exitCondition = true;
                                }
                            }
                            else if (App.isStringInteger(input, 10) && Integer.parseInt(input) == 0) {
                                exitCondition = true;
                            }
                            else {

                                System.out.println("Entrez une valeur");
                            }
                        }
                    }
                }

                this.island.endOfYearResult();
                this.island.updateFoodUnitsComparedToAgriculture();
                this.island.updateTreasuryComparedToIndustry();
                this.seasons = 1;
            }
            else {
                this.seasons++;
            }

            previousEvent = event;            
        }
        this.seasons = 1;
    }

    public void displaySeason() {

        switch (this.seasons) {

            case 1 :
                System.out.println("Eté");
                break;
            case 2 :
                System.out.println("Automne");
                break;
            case 3:
                System.out.println("Hiver");
                break;
            case 4:
                System.out.println("Printemps");
                break;
        }
    }

    public Events getRandomEvent(Events previousEvent) {

        Random random = new Random();
        List<Events> events = this.island.getEvents();
        Events event = events.get(random.nextInt(events.size()));
        if (previousEvent != null) {
            while (event == previousEvent) {
                event = events.get(random.nextInt(events.size()));
            }
        }
        if (event.getSeasons() != 0 && event.getSeasons() != this.seasons) {
            event = events.get(random.nextInt(events.size()));
        }

        return event;
    }

    public void updateFromChoice(Island island, List<Effect> effects) {

        List<Faction> factions = island.getFactions();

        updateFactionFromChoice(factions, effects);
        updateFactorFromChoice(island, effects);
        updatePartisansFromChoice(factions, effects);
    }

    public void updateFactionFromChoice(List<Faction> factions, List<Effect> effects) {

        HashMap<String, Integer> actionOnFaction;

        for (Faction faction: factions) {
            for (Effect effect: effects) {
                actionOnFaction = effect.getActionOnFaction();
                for (String i : actionOnFaction.keySet()) {
                    if (i.toLowerCase().equals(faction.getName().toLowerCase())) {
                        faction.updateSatisfaction((int) (actionOnFaction.get(i) * this.island.getDifficulty()));
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
                        if (island.cumulation(actionOnFactor.get(i), 0)) {
                            island.updateAgriculturePercentage((int) (actionOnFactor.get(i) * this.island.getDifficulty()));
                        }
                        break;
                    case "INDUSTRY":
                        if (island.cumulation(0, actionOnFactor.get(i))) {
                            island.updateIndustryPercentage((int) (actionOnFactor.get(i) * this.island.getDifficulty()));
                        }
                        break;
                    case "TREASURY":
                        island.updateTreasury((int) (actionOnFactor.get(i) * this.island.getDifficulty()));
                        break;
                }
            }
        }
    }
    public void updatePartisansFromChoice(List<Faction> factions, List<Effect> effects){
        int partisans;

        for (Effect effect: effects) {
            partisans = (int) (effect.getPartisans() * this.island.getDifficulty());
            Random random = new Random();

            while (partisans != 0){
                if(partisans<0){
                    factions.get(random.nextInt(8)).updatePartisans(-1);
                    partisans++;
                }
                else {
                    factions.get(random.nextInt(8)).updatePartisans(1);
                    partisans--;
                }
            }
        }
    }
}
