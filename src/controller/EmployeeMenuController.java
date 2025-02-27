package controller;

import model.services.EmployeeManager;
import views.ChoiceMenuView;

import java.util.Scanner;

public class EmployeeMenuController {

    public void buildHumanResourceMenu(EmployeeManager employeeManager, Scanner scanner) {

        String[] humanResourceMenu = {
                "Mitarbeiter hinzufügen",
                "Mitarbeiter anzeigen",
                "Mitarbeiter entfernen",
                "Personendaten ändern",
                "Fuhrpark Zugriff ändern"};

        int menuChoice;

        //User is kept in loop until he chooses to go back to the last Menu

        while(true) {
            menuChoice = ChoiceMenuView.BuildChoiceMenuWithBackOption(
                    "Was möchten Sie tun?",
                    humanResourceMenu,
                    scanner, true);

            switch (menuChoice) {
                case 1:
                    employeeManager.addEmployee(scanner);
                    break;
                case 2:
                    employeeManager.showEmployees(scanner);
                    break;
                case 3:
                    employeeManager.deleteEmployee(scanner);
                    break;
                case 4:
                    employeeManager.updateEmployee(scanner);
                    break;
                case 5:
                    employeeManager.updateEmployeeFleetAccess(scanner);
                    break;
                case 6:
                    return;
            }
        }
    }
}
