package model.services;

import model.dataAccess.EmployeeRepository;
import model.dataAccess.RepositoriesProvider;
import model.enteties.employee.Employee;
import model.enteties.employee.EmployeeAttributes;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.validator.UserInputValidator;
import views.ChoiceEmployeeFilterDialog;
import views.ChoiceMenuView;
import views.UpdateEmplyeeDialogView;
import views.WaitAnyKeyDialogView;
import views.inputViews.InputEmployeeView;
import views.inputViews.UserInputItemView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class EmployeeManager {

    private ArrayList<Employee> employeeList;
    private final EmployeeRepository employeeRepository;



    public EmployeeManager(RepositoriesProvider repositoriesProvider) {
        this.employeeRepository = repositoriesProvider.getEmployeeRepository();
        this.employeeList = this.employeeRepository.selectEmployeesFromTable();
    }



    public void addEmployee(Scanner scanner) {

        if (this.employeeList == null) {
            throw new NullPointerException("employeeList wurde nicht gefüllt");
        }

        while (true) {

           Employee newEmployee = InputEmployeeView.inputEmployee(this.employeeRepository, scanner);

            if (newEmployee == null) {
                return;
            }

            this.employeeList.add(newEmployee);
            this.employeeRepository.addEmployeeToDatabase(newEmployee);

            System.out.println("Mitarbeiter wurde gespeichert\n");
            if (!UserInputValidator.isYesNo("Möchten Sie eine weitere Person eingeben?", scanner)) {
                return;
            }
        }
    }


    public Employee chooseEmployeeFromAllEmployees(Scanner scanner) {
        return chooseEmployee(this.employeeList, scanner);
    }

    //Method for choosing an Employee
    //Method returns the Employee as Employee Object
    public Employee chooseEmployee(ArrayList<Employee> employees, Scanner scanner) {

        ArrayList<Employee> filteredEmployees;

        if (ChoiceMenuView.askYesNo("Möchten Sie die Personen gefiltert auswählen?", scanner))
            filteredEmployees = filterEmployees(employees, scanner);
        else
            filteredEmployees = employees;

        ArrayList<String> employeesAsStrings = employeesToStringsForChoiceMenu(filteredEmployees);

        //Checking if optionList has choices
        if (employeesAsStrings.isEmpty()) {
            System.out.println("Keine Personen zum Auswählen vorhanden");
            return null;
        }

        //getting the user-choice
        int employeeChoice = ChoiceMenuView.BuildChoiceMenu(
                "Wählen Sie eine Person aus", employeesAsStrings, scanner);

        //returning the chosen Employee
        return filteredEmployees.get(employeeChoice-1);
    }


    //Method for deleting Employees in the Database
    public void deleteEmployee(Scanner scanner) {

        Employee chosenEmployee = chooseEmployeeFromAllEmployees(scanner);

        //deleting the chosen Employee if the user agrees
        if (ChoiceMenuView.askYesNo("Möchten Sie die Person wirklich löschen?", scanner)) {
            this.employeeRepository.deleteEmployeeFromDatabase(chosenEmployee);
            this.employeeList.remove(chosenEmployee);
        } else {
            System.out.println("Person wurde nicht entfernt");
        }

    }

    //Method for displaying Employees
    public void showEmployees(Scanner scanner) {

        if (this.employeeList == null) {
            throw new NullPointerException("employeeList wurde nicht gefüllt");
        }

        ArrayList<Employee> filteredEmployees;

        if (ChoiceMenuView.askYesNo("Möchten Sie die Personen gefiltert anzeigen?", scanner))
            filteredEmployees = filterEmployees(this.employeeList, scanner);
        else
            filteredEmployees = this.employeeList;

        //Displaying the Attributes names (Header)
        System.out.println(Employee.toStringAttributeNamesGermanFormat());

        //Displaying the formatted employees
        for (Employee employee : filteredEmployees) {
            System.out.println(employee.toStringFormat());
        }
        System.out.println();

        //Waiting for User-Input to get out of the overview
        WaitAnyKeyDialogView.waitForAnyKey(scanner);
    }



    public ArrayList<Employee> filterEmployees(ArrayList<Employee> employees, Scanner scanner) {

        //Checking if employeeList has been filled
        if (employees == null) {
            throw new NullPointerException("EmployeeList wurde nicht gefüllt");
        }

        ArrayList<Employee> filteredEmployees = new ArrayList<>();

        var filter = ChoiceEmployeeFilterDialog.choseFilter(scanner);

        switch (filter.getEmployeeAttribute()) {
            case EmployeeAttributes.FIRSTNAME:
                filteredEmployees = applyFilter(filter.getFilter(), Employee::getFirstname);
                break;
            case EmployeeAttributes.LASTNAME:
                filteredEmployees = applyFilter(filter.getFilter(), Employee::getLastname);
                break;
            case EmployeeAttributes.POSITION:
                filteredEmployees = applyFilter(filter.getFilter(), Employee::getPosition);
                break;
            case EmployeeAttributes.POSTCODE:
                filteredEmployees = applyFilter(filter.getFilter(), Employee::getPostcode);
                break;
            case EmployeeAttributes.CITY:
                filteredEmployees = applyFilter(filter.getFilter(), Employee::getPostcode);
            case EmployeeAttributes.FLEETACCESS:
                boolean filterBool = ChoiceMenuView.askYesNo(
                        "Wollen Sie die Mitarbeiter mit Fuhrpark-Zugriff herausfiltern?", scanner);
                //adding the Strings to objectList
                if(filterBool)
                    filteredEmployees = getEmployeesWithFleetAccess();
                break;
        }
        return filteredEmployees;
    }


    public void updateEmployeeFleetAccess(Scanner scanner) {

        Employee chosenEmployee = chooseEmployeeFromAllEmployees(scanner);

        if (chosenEmployee == null) {
            return;
        }

        //change FleetAccess in Object
        boolean isFleetAccess = ChoiceMenuView.askYesNo(
                "Hat der Mitarbeiter Zugriff auf die Fahrzeuge des Fuhrparks?", scanner);

        chosenEmployee.setFleetAccess(isFleetAccess);

        //Update in Database
        this.employeeRepository.updateEmployeeInDatabase(chosenEmployee);

        System.out.println("Fuhrpark Zugriff wurde angepasst.\n");
    }


    public void updateEmployee(Scanner scanner) {

        Employee chosenEmployee = chooseEmployeeFromAllEmployees(scanner);


        Employee updatedEmployee = UpdateEmplyeeDialogView.UpdateEmployee(chosenEmployee, scanner);

        //Updating Entry in database
        this.employeeRepository.updateEmployeeInDatabase(updatedEmployee);

        System.out.println("Mitarbeiterdaten wurden angepasst.\n");
    }


    public ArrayList<String> employeesToStringsForChoiceMenu(ArrayList<Employee> employees) {
        ArrayList<String> employeesAsStrings = new ArrayList<>();

        for (Employee employee : employees) {
            employeesAsStrings.add(employee.toStringForChoiceMenu());
        }
        return employeesAsStrings;
    }



    public ArrayList<Employee> applyFilter(String filterValue, Function<Employee, String> getter) {
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : this.employeeList) {
            if (getter.apply(employee).contains(filterValue)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public ArrayList<Employee> getEmployeesWithFleetAccess() {
        ArrayList<Employee> employeesWithFleetAccess = new ArrayList<>();

        for (Employee employee : this.employeeList) {
            if (employee.isFleetAccess()) {
                employeesWithFleetAccess.add(employee);
            }
        }
        return employeesWithFleetAccess;
    }
}
