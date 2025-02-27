package views;

import controller.VehicleMenuController;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.services.EmployeeManager;
import model.services.FleetManager;

import java.util.Scanner;

public class ChoseVehicleTypeMenuDialog {

    public static VehicleType choseVehicleType(Scanner scanner) {
        String[] vehicleTypeMenu = {
                "PKW",
                "LKW",
                "Motorrad",
                "Fahrrad",
                "Zurück"
        };
        int menuChoice;


        //User is kept in Loop until he goes back to the last Menu
        while (true) {
            menuChoice = ChoiceMenuView. BuildChoiceMenu(
                    "Welchen Fahrzeugtyp möchten Sie verwalten?",
                    vehicleTypeMenu,
                    scanner);

            VehicleType vehicleType = switch (menuChoice) {
                case 1 -> VehicleType.CAR;
                case 2 -> VehicleType.TRUCK;
                case 3 -> VehicleType.MOTORBIKE;
                case 4 -> VehicleType.BIKE;
                default -> null;
            };
            return vehicleType;
        }
    }
}
