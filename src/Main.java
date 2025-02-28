import controller.MainMenuController;
import model.dataAccess.*;
import model.services.EmployeeManager;
import model.services.FleetManager;
import model.services.VehicleMaintenanceManager;

public class Main {
    public static void main(String[] args) {

        DatabaseConfigurator databaseConfigurator = new DatabaseConfigurator();

        RepositoriesProvider repositoriesProvider = new RepositoriesProvider(databaseConfigurator);


        VehicleMaintenanceManager vehicleMaintenanceManager = new VehicleMaintenanceManager(repositoriesProvider);
        EmployeeManager employeeManager = new EmployeeManager(repositoriesProvider);
        FleetManager fleetManager = new FleetManager(repositoriesProvider, vehicleMaintenanceManager);

        MainMenuController mainMenuController = new MainMenuController(fleetManager, employeeManager);
        mainMenuController.buildMainMenu();


    }
}