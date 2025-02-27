package model.enteties.fleet.vehicle;

import model.enteties.fleet.vehicle.attributes.MotorBikeAttributes;
import model.enteties.fleet.vehicle.attributes.VehicleType;

import java.util.HashMap;

public class MotorBike extends MotorVehicle{

    public MotorBike(VehicleType vehicleType, String brand, String model, String natCode, String licensePlate,
                     String fuelType, int mileage, int fuelUsage) {
        super(vehicleType, brand, model, natCode, licensePlate, fuelType, mileage, fuelUsage);
    }

    public MotorBike(VehicleType vehicleType, String brand, String model, String natCode, String licensePlate,
                     String fuelType) {
        super(vehicleType, brand, model, natCode, licensePlate, fuelType);
    }

    public MotorBike(HashMap<MotorBikeAttributes, String> motorBike) {
        super(VehicleType.MOTORBIKE,
                motorBike.get(MotorBikeAttributes.BRAND),
                motorBike.get(MotorBikeAttributes.MODEL),
                motorBike.get(MotorBikeAttributes.NATIONAL_CODE),
                motorBike.get(MotorBikeAttributes.LICENSE_PLATE),
                motorBike.get(MotorBikeAttributes.FUEL_TYPE));
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                getBrand(),
                getModel(),
                getNatCode(),
                getLicensePlate(),
                getFuelType(),
                getMileage(),
                getFuelUsage());
    }

    public static String toStringMotorBikeAttributeNamesGermanFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "Marke"))
                .append(String.format("%-20s", "Modell"))
                .append(String.format("%-20s", "Nationaler Code"))
                .append(String.format("%-20s", "Kennzeichen"))
                .append(String.format("%-20s", "Treibstoff-Typ"))
                .append(String.format("%-20s", "Kilometerstand"))
                .append(String.format("%-20s", "Treibstoffverbrauch"));

        return sb.toString();
    }
}
