package model.services;

import model.dataAccess.DriverAssignmentRepository;
import model.enteties.employee.Employee;
import model.enteties.fleet.driverassignment.DriverAssignment;
import model.enteties.fleet.vehicle.MotorVehicle;
import views.ChoiceMenuView;
import views.WaitAnyKeyDialogView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverAssignmentManager {

    DriverAssignmentRepository driverAssignmentRepository;


    public DriverAssignmentManager(DriverAssignmentRepository driverAssignmentRepository) {
        this.driverAssignmentRepository = driverAssignmentRepository;
    }

    public void updateVehicleDriver(MotorVehicle chosenMotorVehicle, EmployeeManager employeeManager, Scanner scanner){
        // find employees, that have access to fleet
        ArrayList<Employee> employeesWithAccess = employeeManager.getEmployeesWithFleetAccess();

        // check if any employee has access to fleet
        if (employeesWithAccess.isEmpty()) {
            System.out.println("Es gibt keine Mitarbeiter mit Zugang zum Fuhrpark.");
            return;
        }

        // select all assignments from database for chosen vehicle
        ArrayList<DriverAssignment> assignmentsChosenVehicle = driverAssignmentRepository.
                selectDriverAssignmentsByVehicleID(chosenMotorVehicle.getNatCode());

        DriverAssignment openedAssignment = null;
        Employee chosenEmployee;

        // chose employee for a new assignment
        // and check if chosen vehicle does not have already assignment to chosen emplyee
        while (true) {
            // choose employee to assign access
            chosenEmployee = employeeManager.chooseEmployee(employeesWithAccess, scanner);

            openedAssignment = findOpenedDriverAssignment(assignmentsChosenVehicle);

            if (openedAssignment != null && openedAssignment.getEmployeeID() == chosenEmployee.getEmployeeID())
            {
                //if vehicle is assigned to selected driver repeat loop or finish method
                if (ChoiceMenuView
                        .askYesNo("Dieses Fahrzeug ist bereits diesem Fahrer zugewiesen. " +
                                "Möchten Sie einen anderen Fahrer auswählen?", scanner)) {
                    continue;
                }
                else
                    return;
            }
            else
                break;
        }

        // if chosen vehicle already has assignment close it up
        if (openedAssignment != null) {
            openedAssignment.setEndDate(LocalDate.now());
            this.driverAssignmentRepository.updateDriverAssignment(openedAssignment);
        }

        DriverAssignment newAssignment =
                createNewDriverAssignment(chosenEmployee.getEmployeeID(), chosenMotorVehicle.getNatCode());

        driverAssignmentRepository.addNewDriverAssignment(newAssignment);

        System.out.println("Der Fahrer wurde erfolgreich aktualisiert.");
        WaitAnyKeyDialogView.waitForAnyKey(scanner);
    }

    public DriverAssignment findOpenedDriverAssignment(ArrayList<DriverAssignment> assignmentsChosenVehicle){
        DriverAssignment openedAssignment = null;

        for (DriverAssignment driverAssignment : assignmentsChosenVehicle) {
            // looking for opened driverAssignment
            if (driverAssignment.getEndDate() == null)
                openedAssignment = driverAssignment;
        }
        return openedAssignment;
    }

    public DriverAssignment createNewDriverAssignment(int imployeeId, String vehicleNatCode) {

        return new DriverAssignment(
                imployeeId,
                vehicleNatCode,
                LocalDate.now());
    }
}
