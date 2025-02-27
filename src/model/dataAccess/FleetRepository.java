package model.dataAccess;

import model.enteties.fleet.driverassignment.DriverAssignment;
import model.enteties.fleet.vehicle.*;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.enteties.fleet.vehicleMaintenance.VehicleService;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FleetRepository {
    DatabaseConnector dbConnector;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public FleetRepository(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    //Method for getting the vehicleIDs from the database, is needed for validating new IDs
    public ArrayList<String> retrieveVehicleIDs() {

        String query = "select vehicleID from vehicles";
        ArrayList<String> idList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                idList.add(rs.getString("vehicleID"));
            }

        } catch (SQLException e) {
            System.err.println("Daten konnten nicht geladen werden, überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
        return idList;
    }

    //Method for inserting new Data into Database
    public void addNewVehicle(Vehicle vehicle) {

        //choosing right statement for the query
        String query = switch (vehicle) {
            case Car car ->
                    "INSERT INTO " +
                    "vehicles (vehicleID, vehicleType, brand, model, licenseplate, fuelType, mileage, fuelUsage, seats) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            case Truck truck ->
                    "NSERT INTO " +
                    "vehicles (vehicleID, vehicleType, brand, model, licenseplate, fuelType, mileage, fuelUsage, transportvolume) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            case MotorBike motorBike ->
                    "NSERT INTO " +
                    "vehicles (vehicleID, vehicleType, brand, model, licenseplate, fuelType, mileage, fuelUsage) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            case Bike bike ->
                    "NSERT INTO " +
                    "vehicles (vehicleID, vehicleType, brand, model) " +
                    "VALUES (?, ?, ?, ?)";
            default -> "";
        };

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            //depending on the type of object, the Method inserts the right data into the statement
            switch (vehicle) {
                case Car car -> {
                    ps.setString(1, car.getNatCode());
                    ps.setString(2, String.valueOf(car.getVehicleType()));
                    ps.setString(3, car.getBrand());
                    ps.setString(4, car.getModel());
                    ps.setString(5, car.getLicensePlate());
                    ps.setString(6, car.getFuelType());
                    ps.setInt(7, car.getMileage());
                    ps.setInt(8, car.getFuelUsage());
                    ps.setInt(9, car.getSeats());
                }
                case Truck truck -> {
                    ps.setString(1, truck.getNatCode());
                    ps.setString(2, String.valueOf(truck.getVehicleType()));
                    ps.setString(3, truck.getBrand());
                    ps.setString(4, truck.getModel());
                    ps.setString(5, truck.getLicensePlate());
                    ps.setString(6, truck.getFuelType());
                    ps.setInt(7, truck.getMileage());
                    ps.setInt(8, truck.getFuelUsage());
                    ps.setInt(9, truck.getTransportCapacity());
                }
                case MotorBike motorBike -> {
                    ps.setString(1, motorBike.getNatCode());
                    ps.setString(2, String.valueOf(motorBike.getVehicleType()));
                    ps.setString(3, motorBike.getBrand());
                    ps.setString(4, motorBike.getModel());
                    ps.setString(5, motorBike.getLicensePlate());
                    ps.setString(6, motorBike.getFuelType());
                    ps.setInt(7, motorBike.getMileage());
                    ps.setInt(8, motorBike.getFuelUsage());
                }
                case Bike bike -> {
                    ps.setString(1, bike.getInventoryNumber());
                    ps.setString(2, String.valueOf(bike.getVehicleType()));
                    ps.setString(3, bike.getBrand());
                    ps.setString(4, bike.getModel());
                }
                default -> {}
            }

            //execution of the statement
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Daten konnten nicht übertragen werden, überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }



    public void addVehicleService(VehicleService vehicleService) {

        String query = "insert into services (vehicleID, serviceDate, mileage) " +
                "values (?, ?, ?)";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){


            ps.setString(1, vehicleService.vehicleID());
            ps.setDate(2, (Date.valueOf(vehicleService.serviceDate())));
            ps.setInt(3, vehicleService.mileage());


            //execution of the statement
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Daten konnten nicht übertragen werden, überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }


    //Method for retrieving Data from the Vehicles Table
    public ArrayList<Vehicle> selectAllVehicles() {
        String query = "SELECT * FROM vehicles";
        ArrayList<Vehicle> vehicleList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String type = rs.getString("vehicleType");
                VehicleType vehicleType = VehicleType.valueOf(type.toUpperCase());

                switch (vehicleType) {
                    case CAR -> vehicleList.add(new Car(vehicleType,
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("vehicleID"),
                            rs.getString("licenseplate"),
                            rs.getString("fuelType"),
                            rs.getInt("mileage"),
                            rs.getInt("fuelUsage"),
                            rs.getInt("seats")));

                    case TRUCK -> vehicleList.add(new Truck(vehicleType,
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("vehicleID"),
                            rs.getString("licenseplate"),
                            rs.getString("fuelType"),
                            rs.getInt("mileage"),
                            rs.getInt("fuelUsage"),
                            rs.getInt("transportVolume")));

                    case MOTORBIKE -> vehicleList.add(new MotorBike(vehicleType,
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("vehicleID"),
                            rs.getString("licenseplate"),
                            rs.getString("fuelType"),
                            rs.getInt("mileage"),
                            rs.getInt("fuelUsage")));

                    case BIKE -> vehicleList.add(new Bike(vehicleType,
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("vehicleID")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Daten konnten nicht geladen werden, überprüfen Sie die Datenbankverbindung: " + e.getMessage());
        }
        return vehicleList;
    }



    //Method for retrieving Data from Services Table
    public ArrayList<VehicleService> selectServicesFromTable() {
        //query for PreparedStatement
        String query = "select * from services";
        //declaring serviceList
        ArrayList<VehicleService> serviceList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                //creating Object with the table-row data
                VehicleService newService = new VehicleService(rs.getString("vehicleID"),
                        rs.getDate("serviceDate").toLocalDate(),
                        rs.getInt("mileage"));
                //adding the Object to the serviceList
                serviceList.add(newService);
            }

        } catch (SQLException e) {
            System.err.println("Daten konnten nicht geladen werden, überprüfen Sie die Datenbankverbindung"
                    + e.getMessage());
        }
        return serviceList;
    }


    //Method for deleting Rows from specific tables
    public void deleteVehicleFromTable(Vehicle vehicleToDelete) {
        //choosing right statement for the query
        String query;

        query = "delete from vehicles where vehicleID = ?";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            //depending on the type of object, the Method inserts the right data into the statement
            switch (vehicleToDelete) {
                case Car car -> ps.setString(1, car.getNatCode());
                case Truck truck -> ps.setString(1, truck.getNatCode());
                case MotorBike motorBike -> ps.setString(1, motorBike.getNatCode());
                case Bike bike -> ps.setString(1, bike.getInventoryNumber());
                default -> {}
            }

            //executing the PreparedStatement
            ps.execute();
        }
        catch (SQLException e) {
            System.err.println("Eintrag konnte nicht aus Datenbank gelöscht werden. Überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }


    //Method for updating Data in Tables, only updates specific columns
    public void updateMileageAndFuelUsageInDatabase(MotorVehicle motorVehicle) {
        //query for PreparedStatement
        String query = "update vehicles set mileage = ?, fuelUsage = ? where vehicleID = ?";

        try  (Connection connection = dbConnector.getConnection();
              PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, motorVehicle.getMileage());
            ps.setInt(2, motorVehicle.getFuelUsage());
            ps.setString(3, motorVehicle.getNatCode());

            //executing the PreparedStatement
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Update der Daten konnte nicht durchgeführt werden. Überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }


}
