package views;

import java.util.Scanner;

public class WaitAnyKeyDialogView {

    public static void waitForAnyKey(Scanner scanner){
        //method to make pause in running before user type
        System.out.println("Drücken Sie Enter.");
        scanner.nextLine();
    }
    public static void waitForAnyKeyAfterNotLine(Scanner scanner){
        //method to make pause in running before user type
        System.out.println("Drücken Sie Enter.");
        scanner.nextLine();
    }
}
