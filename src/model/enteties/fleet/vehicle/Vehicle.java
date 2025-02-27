package model.enteties.fleet.vehicle;

import model.enteties.fleet.vehicle.attributes.VehicleType;

import java.time.Period;

public class Vehicle  {
    private VehicleType vehicleType;
    private String brand;
    private String model;

    static final Period servicePeriod = Period.ofYears(1);

    public Vehicle(VehicleType vehicleType, String brand, String model) {
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public static Period getServicePeriod() {
        return servicePeriod;
    }

    public String toStringChoice() {
        return null;
    }



}
