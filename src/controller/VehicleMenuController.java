package controller;

import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.services.EmployeeManager;
import model.services.FleetManager;
import views.ChoiceMenuView;

import java.util.Scanner;

public class VehicleMenuController {

    public void buildVehicleMenu(FleetManager fleetManager,
                                 EmployeeManager employeeManager,
                                 Scanner scanner) {

        String[] vehicleMenu = {
                "Fahrzeug hinzufügen",
                "Fahrzeuge anzeigen",
                "Fahrzeug entfernen",
                "Fahrzeug bearbeiten",
                "Zurück"
        };


        //User is kept in Loop until he goes back to the last Menu
        int menuChoice;
        while (true) {
            menuChoice = ChoiceMenuView.BuildChoiceMenu(
                    "Was möchten Sie tun?",
                    vehicleMenu,
                    scanner);

            switch (menuChoice) {
                case 1:
                    fleetManager.addVehicle(scanner);
                    break;
                case 2:
                    new ShowVehicleController().buildShowVehicleMenu(fleetManager, scanner);
                    break;
                case 3:
                    fleetManager.deleteVehicle(scanner);
                    break;
                case 4:
                    new VehicleMaintenanceMenu().buildVehicleMaintenanceMenu(fleetManager, employeeManager, scanner);
                    break;
                case 5:
                   return;
            }
        }
    }
}
