package controller;

import model.services.EmployeeManager;
import model.services.FleetManager;
import views.ChoiceMenuView;

import java.util.Scanner;

public class MainMenuController {
    private final FleetManager fleetManager;
    private final EmployeeManager employeeManager;
    private final Scanner scanner;

    public MainMenuController(FleetManager fleetManager, EmployeeManager employeeManager) {
        this.fleetManager = fleetManager;
        this.employeeManager = employeeManager;
        this.scanner = new Scanner(System.in);
    }

    public void buildMainMenu() {

        String[] mainMenu = {
                "Mitarbeiter bearbeiten",
                "Fuhrpark bearbeiten",
                "Programm beenden"};

        int menuChoice;

        //User is kept in loop until he quits the programm

        while (true) {
            menuChoice = ChoiceMenuView.BuildChoiceMenu(
                    "Was möchten Sie tun?",
                    mainMenu, scanner);

            switch (menuChoice) {
                case 1:
                    new EmployeeMenuController().buildHumanResourceMenu(employeeManager, scanner);
                    break;
                case 2:
                    new VehicleMenuController().buildVehicleMenu(
                            fleetManager,
                            employeeManager,
                            scanner);
                    break;
                case 3:
                    System.out.println("Programm wird beendet...");
                    System.exit(0);
            }
        }

    }
}
