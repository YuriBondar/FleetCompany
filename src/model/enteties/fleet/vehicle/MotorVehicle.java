package model.enteties.fleet.vehicle;


import model.enteties.fleet.vehicle.attributes.VehicleType;

public class MotorVehicle extends Vehicle {
    private String natCode;
    private String licensePlate;
    private String fuelType;
    private int mileage;
    private int fuelUsage;


    public MotorVehicle(VehicleType vehicleType, String brand, String model, String natCode,
                        String licensePlate, String fuelType, int mileage, int fuelUsage) {
        super(vehicleType, brand, model);
        this.natCode = natCode;
        this.licensePlate = licensePlate;
        this.fuelType = fuelType;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
    }

    public MotorVehicle(VehicleType vehicleType, String brand, String model, String natCode,
                        String licensePlate, String fuelType) {
        super(vehicleType, brand, model);
        this.natCode = natCode;
        this.licensePlate = licensePlate;
        this.fuelType = fuelType;
        this.mileage = 0;
        this.fuelUsage = 0;

    }

    public String getNatCode() {
        return natCode;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(int fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public String toStringChoice() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-25s", "Fahrzeugtyp: " + getVehicleType()))
                .append(String.format("%-25s", "Marke: " + getBrand()))
                .append(String.format("%-25s", "Modell: " + getModel()))
                .append(String.format("%-25s", "Kennzeichen: " + getLicensePlate()))
                .append(String.format("%-25s", "Nationaler Code: " + getNatCode()));

        return sb.toString();
    }





}
