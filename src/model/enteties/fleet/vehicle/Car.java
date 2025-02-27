package model.enteties.fleet.vehicle;

import model.enteties.fleet.vehicle.attributes.VehicleType;

import java.util.HashMap;

public class Car extends MotorVehicle{
    private int seats;

    public Car(VehicleType vehicleType, String brand, String model, String natCode, String licensePlate,
               String fuelType, int mileage, int fuelUsage, int seats) {
        super(vehicleType, brand, model, natCode, licensePlate, fuelType, mileage, fuelUsage);
        this.seats = seats;

    }

    public Car(VehicleType vehicleType, String brand, String model, String natCode, String licensePlate,
               String fuelType, int seats) {
        super(vehicleType, brand, model, natCode, licensePlate, fuelType);
        this.seats = seats;

    }


    public int getSeats() {
        return seats;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                getBrand(),
                getModel(),
                getNatCode(),
                getLicensePlate(),
                getFuelType(),
                getMileage(),
                getFuelUsage(),
                getSeats());
    }

    public static String toStringCarAttributeNamesGermanFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "Marke"))
                .append(String.format("%-20s", "Modell"))
                .append(String.format("%-20s", "Nationaler Code"))
                .append(String.format("%-20s", "Kennzeichen"))
                .append(String.format("%-20s", "Treibstoff-Typ"))
                .append(String.format("%-20s", "Kilometerstand"))
                .append(String.format("%-20s", "Treibstoffverbrauch"))
                .append(String.format("%-20s", "Sitze"));

        return sb.toString();
    }

}
