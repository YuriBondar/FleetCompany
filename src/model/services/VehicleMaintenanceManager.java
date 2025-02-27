package model.services;

import model.dataAccess.FleetRepository;
import model.enteties.fleet.vehicle.Bike;
import model.enteties.fleet.vehicle.MotorVehicle;
import model.enteties.fleet.vehicle.Vehicle;
import model.enteties.fleet.vehicleMaintenance.VehicleService;
import model.validator.UserInputValidator;
import views.ChoiceMenuView;
import views.WaitAnyKeyDialogView;
import views.inputViews.UserInputItemView;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleMaintenanceManager {

    private  FleetRepository fleetRepository;

    public VehicleMaintenanceManager(FleetRepository fleetRepository) {

        this.fleetRepository = fleetRepository;
    }


    public void updateVehicleService(Vehicle chosenVehicle, Scanner scanner){

        // check, that the vehicleID is the national code ore the inventory number

        String vehicleID;
        int mileage;

        if (chosenVehicle instanceof MotorVehicle) {
            vehicleID = ((MotorVehicle) chosenVehicle).getNatCode();
            mileage = ((MotorVehicle) chosenVehicle).getMileage();
        } else {
            vehicleID = ((Bike) chosenVehicle).getInventoryNumber();
            mileage = 0;
        }

        if(ChoiceMenuView.askYesNo("Die Servicedaten des Fahrzeugs ("+ chosenVehicle.toStringChoice() + ") aktualisieren?", scanner)){



            this.fleetRepository.addVehicleService(new VehicleService(vehicleID, LocalDate.now(), mileage));
            System.out.println("Die Servicedaten wurden erfolgreich aktualisiert.");
        }
    }


    public void checkVehicleService(Vehicle chosenVehicle, Scanner scanner) {
        // load all services from DB
        ArrayList<VehicleService> services = this.fleetRepository.selectServicesFromTable();
        Period period;

        // check, that the vehicleID is the national code ore the inventory number

        String vehicleID;
        if (chosenVehicle instanceof MotorVehicle) {
            vehicleID = ((MotorVehicle) chosenVehicle).getNatCode();
        } else {
            vehicleID = ((Bike) chosenVehicle).getInventoryNumber();
        }

        // display service period
        System.out.println("Servicezeitraum: " + Vehicle.getServicePeriod().getYears() + " Jahr");

        //  display all previous services and find and display the service with last date
        System.out.println("Liste aller durchgeführten Services:");


        System.out.println(VehicleService.toStringAttributesNamesFormat());

        System.out.println();

        VehicleService lastService = null;

        for (VehicleService service : services) {
            if (service.vehicleID().equals(vehicleID)) {
                System.out.println(service);

                if (lastService == null || service.serviceDate().isAfter(lastService.serviceDate())) {
                    lastService = service;
                }
            }

            // calculate next service date
            LocalDate nextService = lastService.serviceDate().plus(Vehicle.getServicePeriod());


            // print message about the remaining or expired time to next service
            String formattedPeriod;
            //case when the service date has not expired
            if (LocalDate.now().isBefore(nextService)) {

                //calculate Period between today end the next service date
                period = Period.between(LocalDate.now(), nextService);

                // convert period to string
                formattedPeriod = periodToString(period);

                // display info about the remaining time
                System.out.println("Nächster VehicleService muss am " + nextService.plus(period) +
                        " in " + formattedPeriod + " durchgeführt werden.");

                //case when the service date has expired
            } else if (LocalDate.now().isAfter(nextService)) {
                //calculate Period between today end the next service date
                period = Period.between(nextService, LocalDate.now());

                // convert period to string
                formattedPeriod = periodToString(period);

                // display info about the expired time
                System.out.println("Der nächste VehicleService hätte vor "
                        + formattedPeriod + " Tagen durchgeführt werden sollen.");
                System.out.println("Bitte dringend den VehicleService durchführen!");
                //case when the service date is today
            } else {
                System.out.println("Nächster VehicleService muss heute durchgeführt werden.");
                System.out.println("Bitte dringend den VehicleService durchführen!");
            }
            WaitAnyKeyDialogView.waitForAnyKey(scanner);
        }
    }


    public void updateMileage(Vehicle chosenVehicle, Scanner scanner){

        MotorVehicle chosenMotorVehicle = (MotorVehicle)chosenVehicle;

        while (true) {

            String newMileage = UserInputItemView.inputItemNoExit(
                    "Geben Sie den neuen Kilometerstand ein",
                    "",
                    UserInputValidator::isIntMoreThan0, scanner);

            // check if current mileage is less then the new one
            // if yes save it to DB
            // if no go to the next loop
            if (UserInputValidator.validatorMileage(chosenMotorVehicle.getMileage(), newMileage)) {

                chosenMotorVehicle.setMileage(Integer.parseInt(newMileage));
                this.fleetRepository.updateMileageAndFuelUsageInDatabase(chosenMotorVehicle);

                System.out.println("Der Kilometerstand wurde erfolgreich aktualisiert.");
                WaitAnyKeyDialogView.waitForAnyKey(scanner);
                return;
            } else {
                WaitAnyKeyDialogView.waitForAnyKey(scanner);
            }
        }
    }

    // add new fuel usage to current
    public void updateFuelUsage(Vehicle chosenVehicle, Scanner scanner){

        MotorVehicle chosenMotorVehicle = (MotorVehicle)chosenVehicle;


        String fuelRefilled = UserInputItemView.inputItemNoExit(
                "Geben Sie an, wie viele Liter das Fahrzeug betankt wurden.",
                "", UserInputValidator::isIntMoreThan0, scanner);

        int newFuelUsage = chosenMotorVehicle.getFuelUsage() + Integer.parseInt(fuelRefilled);

        chosenMotorVehicle.setFuelUsage(newFuelUsage);
        this.fleetRepository.updateMileageAndFuelUsageInDatabase(chosenMotorVehicle);

        System.out.println("Der Kraftstoffverbrauch wurden erfolgreich aktualisiert.");
        WaitAnyKeyDialogView.waitForAnyKey(scanner);
    }

    // concert period to string
    // include years, month or day to string if they are not 0
    public String periodToString(Period period){
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        return  (years > 0 ? years + " Jahr " : "") +
                (months > 0 ? months + " Monate " : "") +
                (days > 0 ? days + " Tage" : "");
    }


}
