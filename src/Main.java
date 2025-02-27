import controller.MainMenuController;
import model.dataAccess.*;
import model.services.EmployeeManager;
import model.services.FleetManager;
import model.services.VehicleMaintenanceManager;

public class Main {
    public static void main(String[] args) {

        DatabaseConfigurator databaseConfigurator = new DatabaseConfigurator();
        DatabaseConnector databaseConnector = new DatabaseConnector(databaseConfigurator);

        EmployeeRepository employeeRepository = new EmployeeRepository(databaseConnector);
        FleetRepository fleetRepository = new FleetRepository(databaseConnector);
        DriverAssignmentRepository driverAssignmentRepository = new DriverAssignmentRepository(databaseConnector);

        VehicleMaintenanceManager vehicleMaintenanceManager = new VehicleMaintenanceManager(fleetRepository);
        EmployeeManager employeeManager = new EmployeeManager(employeeRepository);
        FleetManager fleetManager =
                new FleetManager(
                fleetRepository,
                vehicleMaintenanceManager,
                driverAssignmentRepository);

        MainMenuController mainMenuController = new MainMenuController(fleetManager, employeeManager);
        mainMenuController.buildMainMenu();


    }
}