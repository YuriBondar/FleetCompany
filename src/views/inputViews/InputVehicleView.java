package views.inputViews;

import model.dataAccess.FleetRepository;
import model.enteties.fleet.vehicle.*;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.validator.UserInputValidator;

import java.util.Scanner;

import static views.inputViews.UserInputItemView.inputItem;


public class InputVehicleView {

    public static Car inputCar(FleetRepository fleetRepository, Scanner scanner) {

        MotorVehicle baseVehicle = inputMotorVehicle(fleetRepository, scanner);
        if (baseVehicle == null) return null;

        String seatsStr = inputItem(
                "Geben Sie die Anzahl der Sitze ein:",
                "0-7",
                UserInputValidator::isSeatsNumber, scanner);

        if (seatsStr == null) return null;

        int seats = Integer.parseInt(seatsStr);

        return new Car(
                VehicleType.CAR,
                baseVehicle.getNatCode(),
                baseVehicle.getBrand(),
                baseVehicle.getModel(),
                baseVehicle.getLicensePlate(),
                baseVehicle.getFuelType(),
                seats);
    }


    public static Truck inputTruck(FleetRepository fleetRepository, Scanner scanner) {

        MotorVehicle baseVehicle = inputMotorVehicle(fleetRepository, scanner);
        if (baseVehicle == null) return null;

        String capacityStr = inputItem(
                "Geben Sie die Transportkapazität ein:",
                "Ganze Zahl, kg",
                UserInputValidator::isIntMoreThan0, scanner);
        if (capacityStr == null) return null;

        int transportCapacity = Integer.parseInt(capacityStr);
        return new Truck(
                VehicleType.TRUCK,
                baseVehicle.getNatCode(),
                baseVehicle.getBrand(),
                baseVehicle.getModel(),
                baseVehicle.getLicensePlate(),
                baseVehicle.getFuelType(),
                transportCapacity);
    }


    public static MotorBike inputMotorBike(FleetRepository fleetRepository, Scanner scanner) {
        MotorVehicle baseVehicle = inputMotorVehicle(fleetRepository, scanner);
        if (baseVehicle == null) return null;

        return new MotorBike(
                VehicleType.MOTORBIKE,
                baseVehicle.getNatCode(),
                baseVehicle.getBrand(),
                baseVehicle.getModel(),
                baseVehicle.getLicensePlate(),
                baseVehicle.getFuelType());
    }

    public static Bike inputBike(FleetRepository fleetRepository, Scanner scanner) {

        String inventoryNumber = UserInputItemView.inputItemWithValidation(
                "Geben Sie die Inventarnummer ein:",
                "FR-XXX",
                UserInputValidator::validatorBike,
                fleetRepository, scanner);
        if (inventoryNumber == null) return null;

        String brand = inputItem(
                "Geben Sie die Marke ein.",
                "KTM, APOLLO",
                UserInputValidator::isName, scanner);
        if (brand == null) return null;

        String model = inputItem(
                "Geben Sie das Modell ein.",
                "Rock, Spark5",
                UserInputValidator::isVehicleModel, scanner);
        if (model == null) return null;

        return new Bike(
                VehicleType.BIKE,
                inventoryNumber,
                brand,
                model);
    }


    public static MotorVehicle inputMotorVehicle(FleetRepository fleetRepository, Scanner scanner) {

        String nationalCode = UserInputItemView.inputItemWithValidation(
                "Geben Sie die Nationale Nummer ein.",
                "Eine Zahl mit 6 Ziffern",
                UserInputValidator::validatorMotorVehicle,
                fleetRepository, scanner);
        if (nationalCode == null) return null;

        String brand = inputItem(
                "Geben Sie die Marke ein",
                "BMW, TOYOTA",
                UserInputValidator::isName, scanner);
        if (brand == null) return null;

        String model = inputItem(
                "Geben Sie das Modell ein.",
                "Model 5, CX-8",
                UserInputValidator::isVehicleModel, scanner);
        if (model == null) return null;

        String licensePlate = inputItem(
                "Geben Sie das Kennzeichen des Fahrzeugs ein.",
                "1-2 Buchstaben + 1-4 Ziffern + 1-2 Buchstaben",
                UserInputValidator::isLicensePlate, scanner);
        if (licensePlate == null) return null;

        String fuelType = inputItem(
                "Geben Sie den Kraftstofftyp ein.",
                "Benzin oder Diesel",
                UserInputValidator::isFuelType, scanner);
        if (fuelType == null) return null;

        return new MotorVehicle(
                null,
                nationalCode,
                brand,
                model,
                licensePlate,
                fuelType);
    }

}
