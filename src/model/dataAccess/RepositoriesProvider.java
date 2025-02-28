package model.dataAccess;

public class RepositoriesProvider {

    final DatabaseConfigurator databaseConfigurator;

    private final EmployeeRepository employeeRepository;

    private final FleetRepository fleetRepository;

    private final DriverAssignmentRepository driverAssignmentRepository;

    public RepositoriesProvider(DatabaseConfigurator databaseConfigurator) {

        this.databaseConfigurator = databaseConfigurator;
        DatabaseConnector databaseConnector = new DatabaseConnector(this.databaseConfigurator);

        this.employeeRepository = new EmployeeRepository(databaseConnector);
        this.fleetRepository = new FleetRepository(databaseConnector);
        this.driverAssignmentRepository = new DriverAssignmentRepository(databaseConnector);
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public FleetRepository getFleetRepository() {
        return fleetRepository;
    }

    public DriverAssignmentRepository getDriverAssignmentRepository() {
        return driverAssignmentRepository;
    }
}
