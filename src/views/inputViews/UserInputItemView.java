package views.inputViews;

import views.WaitAnyKeyDialogView;

import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class UserInputItemView {

    // method is used for every input Item
    // inputQuery - says what user need to input
    // format - string to show example
    // after input using methods from Checker

    public static String inputItem(String inputQuery, String format, Predicate<String> checkMethod,
                                   Scanner scanner) {
        while (true) {
            // Informing the user what needs to be entered.
            System.out.println(inputQuery);
            // Informing about the format.
            if (!format.isEmpty()) {
                System.out.println("Format : " + format );
            }
            // Informing about exit option
            System.out.println("Um die Dateneingabe zu beenden, geben Sie \"exit\" ein.");

            System.out.print("Ihre Eingabe: ");
            // Processing input. if input = "exit" return null
            // else if checking true return input, otherwise go to next loop

            if (scanner.hasNextLine()) {

                String input = scanner.nextLine();
                if(input.equals("exit")) {
                    return null;
                }

                // check if string is not empty

                else if (input.trim().isEmpty()) {
                    System.out.println("Eingabe darf nicht leer sein!");
                    WaitAnyKeyDialogView.waitForAnyKey(scanner);
                    continue;
                }

                else if (checkMethod.test(input)) {
                    System.out.println();
                    return input;
                }

                else {
                    WaitAnyKeyDialogView.waitForAnyKey(scanner);
                }

            } else {
                System.out.println("Eingabefehler");
            }
        }
    }

    public static <T> String inputItemWithValidation(
            String inputQuery,
            String format,
            BiPredicate<String, T> checkMethod,
            T repository,
            Scanner scanner) {

        while (true) {

            System.out.println(inputQuery);

            if (!format.isEmpty()) {
                System.out.println("Format: " + format);
            }

            System.out.println("Um die Dateneingabe zu beenden, geben Sie \"exit\" ein.");
            System.out.print("Ihre Eingabe: ");

            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    return null;
                }


                if (checkMethod.test(input, repository)) {
                    System.out.println();
                    return input;
                } else {
                    WaitAnyKeyDialogView.waitForAnyKey(scanner);
                }
            } else {
                System.out.println("Eingabefehler");
            }
        }
    }

    // method is used for every input Item
    // inputQuery - says what user need to input
    // format - string to show example
    // after input using methods from Checker
    public static String inputItemNoExit(String inputQuery, String format, Predicate<String> checkMethod,
                                         Scanner scanner) {
        while (true) {
            // Informing the user what needs to be entered.
            System.out.println(inputQuery);
            // Informing about the format.
            if (!format.isEmpty()) {
                System.out.println("Format : " + format );
            }

            System.out.print("Ihre Eingabe: ");
            // Processing input. if input = "exit" return null
            // else if checking true return input, otherwise go to next loop

            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (checkMethod.test(input)) {
                    System.out.println();
                    return input;
                } else {
                    WaitAnyKeyDialogView.waitForAnyKey(scanner);
                }

            } else {
                System.out.println("Eingabefehler");
            }
        }
    }


    // method is used for every input Item
    // inputQuery - says what user need to input
    // format - string to show example
    // after input using methods from Checker
    // added extra checking for validation
    public static String inputItemNoExit(String inputQuery, String format, Predicate<String> checkMethod,
                                         Predicate<String> validateMethod, Scanner scanner) {
        while (true) {
            // Informing the user what needs to be entered.
            System.out.println(inputQuery);
            // Informing about the format.
            if (!format.isEmpty()) {
                System.out.println("Format: " + format);
            }
            // Informing about exit option
            System.out.println("Um die Dateneingabe zu beenden, geben Sie \"exit\" ein.");
            System.out.print("Ihre Eingabe: ");
            // Processing input. if input = "exit" return null
            // else if checking true return input, otherwise go to next loop
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                if (checkMethod.test(input)) {
                    if (validateMethod.test(input)) {
                        System.out.println();
                        return input;
                    }
                } else {
                    WaitAnyKeyDialogView.waitForAnyKey(scanner);
                }
            } else {
                System.out.println("Eingabefehler");
            }
        }
    }
}
