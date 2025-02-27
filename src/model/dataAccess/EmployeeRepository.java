package model.dataAccess;

import model.enteties.employee.Employee;
import model.enteties.fleet.driverassignment.DriverAssignment;
import model.enteties.fleet.vehicle.Bike;
import model.enteties.fleet.vehicle.Car;
import model.enteties.fleet.vehicle.MotorBike;
import model.enteties.fleet.vehicle.Truck;
import model.enteties.fleet.vehicleMaintenance.VehicleService;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeRepository {

    private final DatabaseConnector dbConnector;

    private static PreparedStatement ps;
    private static ResultSet rs;

    public EmployeeRepository(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;

    }


    //Method for retrieving Data from the Employees Table
    public ArrayList<Employee> selectEmployeesFromTable() {

        String query = "SELECT * FROM employees";
        ArrayList<Employee> employeeList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee newEmployee = new Employee(
                        rs.getInt("employeeID"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("position"),
                        rs.getString("socialsecuritynumber"),
                        rs.getDate("birthday").toLocalDate(),
                        rs.getString("streetname"),
                        rs.getString("houseNr"),
                        rs.getString("postcode"),
                        rs.getString("city"),
                        rs.getBoolean("fleetAccess")
                );
                employeeList.add(newEmployee);
            }
        }
        catch (SQLException e) {
            System.err.println("Daten konnten nicht geladen werden. Fehler: " + e.getMessage());
        }
        return employeeList;
    }


    public void addEmployeeToDatabase(Employee employee) {

        //choosing right statement for the query
        String query =
                "insert into employees " +
                    "(employeeID, " +
                    "firstname, " +
                    "lastname, " +
                    "position, " +
                    "socialsecuritynumber, " +
                    "birthday, " +
                    "streetname, " +
                    "houseNr, " +
                    "postcode, " +
                    "city, " +
                    "fleetAccess) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, employee.getEmployeeID());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getLastname());
            ps.setString(4, employee.getPosition());
            ps.setString(5, employee.getSocialsecuritynumber());
            ps.setDate(6, Date.valueOf(employee.getBirthday()));
            ps.setString(7, employee.getStreetname());
            ps.setString(8, employee.getHouseNr());
            ps.setString(9, employee.getPostcode());
            ps.setString(10, employee.getCity());
            ps.setBoolean(11, employee.isFleetAccess());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Daten konnten nicht übertragen werden. Fehler: " + e.getMessage());
        }
    }

    //Method for deleting Rows from specific tables
    public void deleteEmployeeFromDatabase(Employee employee) {

        //choosing right statement for the query
        String query;

        query = "delete from employees where employeeID = ?";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            ps.setInt(1, employee.getEmployeeID());

            //executing the PreparedStatement
            ps.execute();

        } catch (SQLException e) {
            System.err.println("Eintrag konnte nicht aus Datenbank gelöscht werden. Überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }

    //Method for updating Data in Tables, only updates specific columns
    public void updateEmployeeInDatabase(Employee employee) {
        //query for PreparedStatement
        String query;

        query = "update employees " +
                "set firstname = ?, lastname = ?, position = ?, streetname = ?, houseNr = ?, postcode = ?, city = ?, fleetAccess = ? " +
                "where employeeID = ?";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getPosition());
            ps.setString(4, employee.getStreetname());
            ps.setString(5, employee.getHouseNr());
            ps.setString(6, employee.getPostcode());
            ps.setString(7, employee.getCity());
            ps.setBoolean(8, employee.isFleetAccess());
            ps.setInt(9, employee.getEmployeeID());

            //executing the PreparedStatement
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Update der Daten konnte nicht durchgeführt werden. Überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }
}
