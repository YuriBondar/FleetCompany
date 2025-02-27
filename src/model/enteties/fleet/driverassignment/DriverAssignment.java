package model.enteties.fleet.driverassignment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DriverAssignment {
    private int employeeID;
    private String vehicleID;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public DriverAssignment(int employeeID, String vehicleID, LocalDate startDate, LocalDate endDate) {
        this.employeeID = employeeID;
        this.vehicleID = vehicleID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DriverAssignment(int employeeID, String vehicleID, LocalDate startDate) {
        this.employeeID = employeeID;
        this.vehicleID = vehicleID;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String startDateStr = (startDate != null) ? startDate.format(formatter) : "N/A";
        String endDateStr = (endDate != null) ? endDate.format(formatter) : "N/A";

        return String.format("%-20s%-20s", startDateStr, endDateStr);
    }

}
