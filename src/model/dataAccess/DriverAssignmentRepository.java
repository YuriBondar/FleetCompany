package model.dataAccess;

import model.enteties.fleet.driverassignment.DriverAssignment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DriverAssignmentRepository {
    DatabaseConnector dbConnector;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public DriverAssignmentRepository(DatabaseConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    //Method for inserting new Data into Database
    public void addNewDriverAssignment(DriverAssignment driverAssignment) {
        String query = "INSERT INTO driverAssignments (employeeID, vehicleID, startDate) VALUES (?, ?, ?)";

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, driverAssignment.getEmployeeID());
            ps.setString(2, driverAssignment.getVehicleID());
            ps.setDate(3, Date.valueOf(driverAssignment.getStartDate()));

            ps.executeUpdate();


        } catch (SQLException e) {
            System.err.println("Daten konnten nicht übertragen werden: " + e.getMessage());
        }
    }

    //Method for retrieving Data from the DriverAssignments Table
    public ArrayList<DriverAssignment> selectDriverAssignmentsByVehicleID(String vehicleId) {
        //query for PreparedStatement
        String query = "select * from driverAssignments where vehicleID = ?";
        //declaring assignmentList
        ArrayList<DriverAssignment> assignmentList = new ArrayList<>();

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, vehicleId);

            try (ResultSet rs = ps.executeQuery())
            {

                while (rs.next()) {
                    // Получаем данные из результата
                    int employeeID = rs.getInt("employeeID");
                    String vehicleID = rs.getString("vehicleID");
                    LocalDate startDate = rs.getDate("startDate").toLocalDate();

                    Date endDateSQL = rs.getDate("endDate");
                    LocalDate endDate = (endDateSQL != null) ? endDateSQL.toLocalDate() : null;

                    // Создаём объект
                    DriverAssignment newAssignment = (endDate != null) ?
                            new DriverAssignment(employeeID, vehicleID, startDate, endDate) :
                            new DriverAssignment(employeeID, vehicleID, startDate);

                    // Добавляем в список
                    assignmentList.add(newAssignment);
                }
            }

        } catch (SQLException e) {
            System.err.println("Daten konnten nicht geladen werden, überprüfen Sie die Datenbankverbindung"
                    + e.getMessage());
        }
        return assignmentList;
    }


    //Method for updating Data in Tables, only updates specific columns
    public void updateDriverAssignment(DriverAssignment driverAssignment) {
        //query for PreparedStatement
        String query =
                "update driverAssignments " +
                        "set endDate = ? " +
                        "where employeeID = ? and vehicleID = ? and endDate is NULL";


        try  (Connection connection = dbConnector.getConnection();
              PreparedStatement ps = connection.prepareStatement(query)){


            ps.setDate(1, Date.valueOf(driverAssignment.getEndDate()));
            ps.setInt(2, driverAssignment.getEmployeeID());
            ps.setString(3, driverAssignment.getVehicleID());


            //executing the PreparedStatement
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Update der Daten konnte nicht durchgeführt werden. Überprüfen Sie die Datenbankverbindung "
                    + e.getMessage());
        }
    }
}
