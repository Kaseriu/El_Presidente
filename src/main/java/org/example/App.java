package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main( String[] args ){

        boolean stop = false;
        boolean back;
        Game game = new Game();



        Scanner scanner = new Scanner(System.in);

        while (!stop) {

            System.out.println("EL PRESIDENTE!");
            System.out.println("Appuyer sur entrée pour continuer ou taper stop pour quitter");
            String input = scanner.nextLine();

            back = false;
            switch(input.toLowerCase()) {

                case "stop":
                    stop = true;
                    break;

                case "":
                    while (!back) {
                        System.out.println("Choisissez un scénario ou taper back pour retourner en arrière :");
                        String[][] tab = game.displayScenarios();
                        System.out.println(formattedString(tab));
                        scanner.reset();
                        input = scanner.nextLine();

                        if (!isStringInteger(input, 10)) {
                            if (input.equals("back")) {
                                back = true;
                            }
                            else {
                                System.out.println("Taper un chiffre");
                            }

                        }
                        else {
                            if (Integer.parseInt(input) > 4 || Integer.parseInt(input) <= 0) {

                                System.out.println("Numéro de scénario invalide");
                            }
                            else {
                                game.launchScenario(tab[Integer.parseInt(input) - 1][1]);
                                game.displayChosenScenario();
                                System.out.println();
                                game.gameStart();
                            }
                        }

                    }
                    break;

                default:
                    System.out.println("Mauvaise saisie");
            }
        }
    }

    public static String formattedString(String[][] tab) {

        return Arrays.deepToString(tab)
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
    }

    public static boolean isStringInteger(String stringToCheck, int radix) {

        if(stringToCheck.isEmpty()) {
            return false;           //Check if the string is empty
        }
        for (int i = 0; i < stringToCheck.length(); i++) {
            if (i == 0 && stringToCheck.charAt(i) == '-') {     //Check for negative Integers
                if (stringToCheck.length() == 1) {
                    return false;
                }
                else {
                    continue;
                }
            }
            if (Character.digit(stringToCheck.charAt(i),radix) < 0) {
                return false;
            }
        }
        return true;
    }
}
