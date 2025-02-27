package model.enteties.fleet.vehicle;

import model.enteties.fleet.vehicle.attributes.BikeAttributes;
import model.enteties.fleet.vehicle.attributes.VehicleType;

import java.util.HashMap;

public class Bike extends Vehicle{
    private String inventoryNumber;

    public Bike(VehicleType vehicleType, String brand, String model, String inventoryNumber) {
        super(vehicleType, brand, model);
        this.inventoryNumber = inventoryNumber;
    }

    public Bike(HashMap<BikeAttributes, String> bike){
        super(VehicleType.BIKE,
                bike.get(BikeAttributes.BRAND),
                bike.get(BikeAttributes.MODEL));
        this.inventoryNumber = bike.get(BikeAttributes.INVENTORY_NUMBER);
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public String toStringChoice() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "Fahrzeugtyp: " + getVehicleType()))
                .append(String.format("%-20s", "Marke: " + getBrand()))
                .append(String.format("%-20s", "Modell: " + getModel()))
                .append(String.format("%-20s", "Inventarnummer: " + getInventoryNumber()));

        return sb.toString();
    }


    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s",
                getBrand(),
                getModel(),
                getInventoryNumber());
    }

    public static String toStringBikeAttributeNamesGermanFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "Marke"))
                .append(String.format("%-20s", "Modell"))
                .append(String.format("%-20s", "Inventarnummer"));

        return sb.toString();
    }

}
