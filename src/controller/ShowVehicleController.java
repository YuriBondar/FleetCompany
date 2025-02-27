package controller;

import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.services.EmployeeManager;
import model.services.FleetManager;
import views.ChoiceMenuView;

import java.util.Scanner;

public class ShowVehicleController {
    public void buildShowVehicleMenu(FleetManager fleetManager,
                                 Scanner scanner) {

        String[] vehicleMenu = {
                "PKW anzeigen",
                "LKW anzeigen",
                "Motorrad anzeigen",
                "Fahrrad anzeigen",
                "Alle Fahrzeuge anzeigen",
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
                    fleetManager.showVehicles(VehicleType.CAR, scanner);
                    break;
                case 2:
                    fleetManager.showVehicles(VehicleType.TRUCK, scanner);
                    break;
                case 3:
                    fleetManager.showVehicles(VehicleType.MOTORBIKE, scanner);
                    break;
                case 4:
                    fleetManager.showVehicles(VehicleType.BIKE, scanner);
                    break;
                case 5:
                    fleetManager.showAllVehicles(scanner);
                    break;
                case 6:
                    return;
            }
        }
    }
}
