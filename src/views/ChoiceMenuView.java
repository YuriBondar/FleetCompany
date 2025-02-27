package views;

import model.validator.UserInputValidator;

import java.util.ArrayList;
import java.util.Scanner;


public class ChoiceMenuView {

    public static int BuildChoiceMenu(String query, String[] optionList, Scanner scanner){
        // The method displays a numbered list (from Array) on the screen,
        // from which the user needs to make a choice by entering the corresponding number.
        // Query is an explanation to the user about what they need to select.

        String userChoice;
        // display query
        System.out.println(query);

        // display numbered list
        while (true) {
            int i = 0;
            for (String item : optionList) {
                i++;
                System.out.printf("%-5s%s%n",i + ". ",item);
            }

            System.out.print("Ihre Auswahl: ");

            userChoice = scanner.nextLine();

            // check if user choice is number from Menu
            if (!UserInputValidator.isIntInRange(userChoice, 1, i)){
                WaitAnyKeyDialogView.waitForAnyKeyAfterNotLine(scanner);
                continue;
            }
            return Integer.parseInt(userChoice);
        }
    }

    public static int BuildChoiceMenuWithBackOption(String query, String[] optionList, Scanner scanner, boolean backOption){
        // The method displays a numbered list (from Array) on the screen,
        // from which the user needs to make a choice by entering the corresponding number.
        // Query is an explanation to the user about what they need to select.

        String userChoice;
        // display query
        System.out.println(query + ":");

        // display numbered list
        while (true) {
            int i = 0;
            for (String item : optionList) {
                i++;
                System.out.printf("%-5s%s%n",i + ". ",item);
            }

            //optional choice for going back out of the menu without choice
            if (backOption) {
                i++;
                System.out.printf("%-5s%s%n",i + ". ","Zurück");
            }

            System.out.print("Ihre Auswahl: ");

            userChoice = scanner.nextLine();


            if (!UserInputValidator.isIntInRange(userChoice, 1, i)){
                WaitAnyKeyDialogView.waitForAnyKeyAfterNotLine(scanner);
                continue;
            }

            return Integer.parseInt(userChoice);
        }
    }

    public static int BuildChoiceMenu(String query, ArrayList<String> optionList, Scanner scanner) {
        // The method displays a numbered list (from Arraylist) on the screen,
        // from which the user needs to make a choice by entering the corresponding number.
        // Query is an explanation to the user about what they need to select.

        String userChoice;
        // display query
        System.out.println(query);

        // display numbered list
        while (true) {
            int i = 0;
            for (String item : optionList) {
                i++;
                System.out.printf("%-5s%s%n",i + ". ",item);
            }

            System.out.print("Ihre Auswahl: ");

            userChoice = scanner.nextLine();

            if (!UserInputValidator.isIntInRange(userChoice, 1, i)){
                WaitAnyKeyDialogView.waitForAnyKeyAfterNotLine(scanner);
                continue;
            }
            return Integer.parseInt(userChoice);
        }
    }

    public static int BuildChoiceMenuWithBackOption(String query, ArrayList<String> optionList, Scanner scanner, boolean backOption) {
        // The method displays a numbered list (from Arraylist) on the screen,
        // from which the user needs to make a choice by entering the corresponding number.
        // Query is an explanation to the user about what they need to select.

        String userChoice;
        // display query
        System.out.println(query + ":");

        // display numbered list
        while (true) {
            int i = 0;
            for (String item : optionList) {
                i++;
                System.out.printf("%-5s%s%n",i + ". ",item);
            }

            //optional choice for going back out of the menu without choice
            if (backOption) {
                i++;
                System.out.printf("%-5s%s%n",i + ". ","Zurück");
            }

            System.out.print("Ihre Auswahl: ");

            userChoice = scanner.nextLine();


            if (!UserInputValidator.isIntInRange(userChoice, 1, i)){
                WaitAnyKeyDialogView.waitForAnyKeyAfterNotLine(scanner);
                continue;
            }
            return Integer.parseInt(userChoice);
        }
    }

    public static boolean askYesNo(String query, Scanner scanner) {
        System.out.println(query);
        while (true) {
            System.out.println("Drücken Sie y/n (y für Ja/n für Nein)");
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("y")) {
                    return true;
                } else if (input.equals("n")) {
                    return false;
                } else {
                    System.out.println("Ungültige Eingabe.");
                }
            } else {
                return false;
            }
        }
    }
}
