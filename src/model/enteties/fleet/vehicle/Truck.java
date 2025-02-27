package model.enteties.fleet.vehicle;

import model.enteties.fleet.vehicle.attributes.TruckAttributes;
import model.enteties.fleet.vehicle.attributes.VehicleType;

import java.util.HashMap;

public class Truck extends MotorVehicle{
    private int transportCapacity;

    public Truck(VehicleType vehicleType, String brand, String model, String natCode, String licensePlate,
                 String fuelType, int mileage, int fuelUsage, int transportCapacity) {
        super(vehicleType, brand, model, natCode, licensePlate, fuelType, mileage, fuelUsage);
        this.transportCapacity = transportCapacity;
    }

    public Truck(VehicleType vehicleType, String brand, String model, String natCode, String licensePlate,
                 String fuelType, int transportCapacity) {
        super(vehicleType, brand, model, natCode, licensePlate, fuelType);
        this.transportCapacity = transportCapacity;
    }

    public Truck(HashMap<TruckAttributes, String> truck){
        super(VehicleType.TRUCK,
                truck.get(TruckAttributes.BRAND),
                truck.get(TruckAttributes.MODEL),
                truck.get(TruckAttributes.NATIONAL_CODE),
                truck.get(TruckAttributes.LICENSE_PLATE),
                truck.get(TruckAttributes.FUEL_TYPE));
        this.transportCapacity = Integer.parseInt(truck.get(TruckAttributes.TRANSPORT_CAPACITY));
    }

    public int getTransportCapacity() {
        return transportCapacity;
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
                getTransportCapacity());
    }

    public static String toStringTruckAttributeNamesGermanFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "Marke"))
                .append(String.format("%-20s", "Modell"))
                .append(String.format("%-20s", "Nationaler Code"))
                .append(String.format("%-20s", "Kennzeichen"))
                .append(String.format("%-20s", "Treibstoff-Typ"))
                .append(String.format("%-20s", "Kilometerstand"))
                .append(String.format("%-20s", "Treibstoffverbrauch"))
                .append(String.format("%-20s", "Transport-Kapazität"));

        return sb.toString();
    }
}
