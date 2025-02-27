package controller;

import model.enteties.fleet.vehicle.MotorVehicle;
import model.enteties.fleet.vehicle.Vehicle;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.services.EmployeeManager;
import model.services.FleetManager;
import views.ChoiceMenuView;

import java.util.Scanner;

public class VehicleMaintenanceMenu {

    public void buildVehicleMaintenanceMenu(FleetManager fleetManager,
                                            EmployeeManager employeeManager,
                                            Scanner scanner) {

        Vehicle chosenVehicle = fleetManager.chooseVehicle(scanner);

        if (chosenVehicle == null) {
            return;
        }

        VehicleType  vehicleType = fleetManager.defineVehicleType(chosenVehicle);

        String[] vehicleMaintenanceMenu;
        int menuChoice;

        if (vehicleType.equals(VehicleType.BIKE)) {
            vehicleMaintenanceMenu = new String[]{
                    "Service überprüfen",
                    "Service eintragen",
                    "Zurück"};

            while (true) {
                menuChoice = ChoiceMenuView.BuildChoiceMenu(
                        "Was möchten Sie tun?",
                        vehicleMaintenanceMenu,
                        scanner);

                switch (menuChoice) {
                    case 1:
                        fleetManager.checkVehicleService(chosenVehicle, scanner);
                        break;
                    case 2:
                        fleetManager.updateVehicleService(chosenVehicle, scanner);
                        break;
                    case 3:
                        return;
                }
            }
        }
        else {
            vehicleMaintenanceMenu = new String[]{
                    "Fahrer auswählen",
                    "Kilometerstand anpassen",
                    "Tanken",
                    "Service überprüfen",
                    "Service eintragen",
                    "Zurück"};

            while (true) {
                menuChoice = ChoiceMenuView.BuildChoiceMenu(
                        "Was möchten Sie tun?",
                        vehicleMaintenanceMenu,
                        scanner);

                switch (menuChoice) {
                    case 1:
                        fleetManager.updateVehicleDriver((MotorVehicle)chosenVehicle, employeeManager,scanner);
                        break;
                    case 2:
                        fleetManager.updateMileage(chosenVehicle, scanner);
                        break;
                    case 3:
                        fleetManager.updateFuelUsage(chosenVehicle, scanner);
                        break;
                    case 4:
                        fleetManager.checkVehicleService(chosenVehicle, scanner);
                        break;
                    case 5:
                        fleetManager.updateVehicleService(chosenVehicle, scanner);
                        break;
                    case 6:
                        return;
                }
            }
        }
    }
}
