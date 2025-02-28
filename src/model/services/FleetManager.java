package model.services;

import model.dataAccess.DriverAssignmentRepository;
import model.dataAccess.FleetRepository;
import model.dataAccess.RepositoriesProvider;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.enteties.fleet.vehicleMaintenance.VehicleService;
import model.enteties.fleet.vehicle.*;
import views.ChoiceMenuView;
import views.ChoseVehicleTypeMenuDialog;
import views.inputViews.InputVehicleView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class FleetManager {

    private ArrayList<Vehicle> vehicleList;

    FleetRepository fleetRepository;
    DriverAssignmentRepository driverAssignmentRepository;

    VehicleMaintenanceManager vehicleMaintenanceManager;
    DriverAssignmentManager driverAssignmentManager;


    public FleetManager(RepositoriesProvider repositoriesProvider,
                        VehicleMaintenanceManager vehicleMaintenanceManager) {
        this.fleetRepository = repositoriesProvider.getFleetRepository();
        this.vehicleMaintenanceManager = vehicleMaintenanceManager;

        this.driverAssignmentRepository = repositoriesProvider.getDriverAssignmentRepository();

        this.vehicleList = this.fleetRepository.selectAllVehicles();

        this.driverAssignmentManager = new DriverAssignmentManager(this.driverAssignmentRepository);

    }


    public void addVehicle(Scanner scanner) {
        VehicleType vehicleType = ChoseVehicleTypeMenuDialog.choseVehicleType(scanner);

        Vehicle newVehicle = null;

        while (true) {
            switch (vehicleType) {
                case CAR ->
                    newVehicle = InputVehicleView.inputCar(this.fleetRepository, scanner);
                case TRUCK ->
                    newVehicle = InputVehicleView.inputTruck(this.fleetRepository, scanner);
                case MOTORBIKE ->
                    newVehicle = InputVehicleView.inputMotorBike(this.fleetRepository, scanner);
                case BIKE ->
                    newVehicle = InputVehicleView.inputBike(this.fleetRepository, scanner);
            }

            if (newVehicle == null)
                return;

            this.vehicleList.add(newVehicle);
            this.fleetRepository.addNewVehicle(newVehicle);

            if (newVehicle instanceof MotorVehicle newMotorVehicle){
                this.fleetRepository.addVehicleService(
                        new VehicleService(newMotorVehicle.getNatCode(), LocalDate.now(), newMotorVehicle.getMileage()));
            }
            if (newVehicle instanceof Bike newBike) {
                this.fleetRepository.addVehicleService(new VehicleService(newBike.getInventoryNumber(), LocalDate.now(), 0));
            }

            System.out.println("Fahrzeug wurde gespeichert\n");
            if (!ChoiceMenuView.askYesNo("Möchten Sie ein weiteres Fahrzeug eingeben?", scanner)) {
                return;
            }
        }
    }


    //Method for choosing a specific Vehicle in the vehicleList
    //Method returns the chosen Vehicle as Object
    public <T> T chooseVehicle(Scanner scanner) {

        ArrayList<String> vehiclesAsStringsForChoiceMenu = vehiclesToStringsForChoiceMenu(this.vehicleList);

        if (vehiclesAsStringsForChoiceMenu.isEmpty()) {
            System.out.println("Kein Fahrzeug zum Auswählen vorhanden");
            return null;
        }

        //getting the user-choice
        int vehicleChoice = ChoiceMenuView.BuildChoiceMenu(
                "Wählen Sie ein Fahrzeug aus", vehiclesAsStringsForChoiceMenu, scanner);

        //returning the chosen Vehicle
        return (T) this.vehicleList.get(vehicleChoice - 1);
    }



    //Method for deleting specific Vehicle in the vehicleList
    public void deleteVehicle(Scanner scanner) {
        Vehicle chosenVehicle = chooseVehicle(scanner);

        //deleting the choice if the user accepts
        if (ChoiceMenuView.askYesNo("Möchten Sie das Fahrzeug wirklich löschen?", scanner)) {

            this.fleetRepository.deleteVehicleFromTable(chosenVehicle);
            this.vehicleList.remove(chosenVehicle);
        }
        else {
            System.out.println("Fahrzeug wurde nicht entfernt.");
        }
    }


    //Method for showing all Vehicles in the vehicleList
    public void showVehicles(VehicleType vehicleType, Scanner scanner) {
        if (this.vehicleList == null) {
            throw new NullPointerException("vehicleList wurde nicht gefüllt");
        }

        //print header depending on the vehicleType
        switch (vehicleType) {
            case CAR:
                System.out.println("PKW");
                System.out.println(Car.toStringCarAttributeNamesGermanFormat());
                break;
            case TRUCK:
                System.out.println("LKW");
                System.out.println(Truck.toStringTruckAttributeNamesGermanFormat());
                break;
            case MOTORBIKE:
                System.out.println("Motorrad");
                System.out.println(MotorBike.toStringMotorBikeAttributeNamesGermanFormat());
                break;
            case BIKE:
                System.out.println("Fahrrad");
                System.out.println(Bike.toStringBikeAttributeNamesGermanFormat());
                break;
        }


        //Displaying the data of the Attributes
        for (Vehicle vehicle : this.vehicleList) {
            if (defineVehicleType(vehicle) == vehicleType) {
                switch (vehicle) {
                    case Car car -> System.out.println(car);
                    case Truck truck -> System.out.println(truck);
                    case MotorBike motorBike -> System.out.println(motorBike);
                    case Bike bike -> System.out.println(bike);
                    default -> {}
                }
            }
        }
    }




    public void showAllVehicles(Scanner scanner) {
        showVehicles(VehicleType.CAR, scanner);
        showVehicles(VehicleType.TRUCK, scanner);
        showVehicles(VehicleType.MOTORBIKE, scanner);
        showVehicles(VehicleType.BIKE, scanner);
    }

    //Method for creating a List with choices for choosing in Menu
    public ArrayList<String> vehiclesToStringsForChoiceMenu(ArrayList<Vehicle> vehicles) {
        ArrayList<String> optionList = new ArrayList<>();

        //Checking if vehicleList has been filled
        if (vehicles == null) {
            throw new NullPointerException("vehicleList wurde nicht gefüllt");
        } else {
            //adding Strings to optionList
            for (Object vehicle : vehicles) {
                switch (vehicle) {
                    case Car car -> optionList.add(car.toStringChoice());
                    case Truck truck -> optionList.add(truck.toStringChoice());
                    case MotorBike motorBike -> optionList.add(motorBike.toStringChoice());
                    case Bike bike -> optionList.add(bike.toStringChoice());
                    default -> {
                    }
                }
            }
        }
        return optionList;
    }


    public void checkVehicleService(Vehicle chosenVehicle, Scanner scanner) {
        this.vehicleMaintenanceManager.checkVehicleService(chosenVehicle, scanner);
    }

    public void updateVehicleService(Vehicle chosenVehicle, Scanner scanner) {
        this.vehicleMaintenanceManager.updateVehicleService(chosenVehicle, scanner);
    }

    public void updateMileage(Vehicle chosenVehicle, Scanner scanner) {
        this.vehicleMaintenanceManager.updateMileage(chosenVehicle, scanner);
    }

    public void updateFuelUsage(Vehicle chosenVehicle, Scanner scanner) {
        this.vehicleMaintenanceManager.updateFuelUsage(chosenVehicle, scanner);
    }

    public void updateVehicleDriver(
            MotorVehicle chosenMotorVehicle,
            EmployeeManager employeeManager,
            Scanner scanner) {

        driverAssignmentManager.updateVehicleDriver(chosenMotorVehicle, employeeManager, scanner);

    }


    public VehicleType defineVehicleType(Vehicle vehicle) {

        return switch (vehicle) {
            case Car car -> VehicleType.CAR;
            case Truck truck -> VehicleType.TRUCK;
            case MotorBike motorBike -> VehicleType.MOTORBIKE;
            case Bike bike -> VehicleType.BIKE;
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };
    }

}
