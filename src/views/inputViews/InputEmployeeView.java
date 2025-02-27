package views.inputViews;

import model.dataAccess.EmployeeRepository;
import model.enteties.employee.Employee;
import model.enteties.employee.EmployeeAttributes;
import model.validator.UserInputValidator;
import views.ChoiceMenuView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

import static views.inputViews.UserInputItemView.inputItem;
import static views.inputViews.UserInputItemView.inputItemWithValidation;

public class InputEmployeeView {


    public static Employee inputEmployee(EmployeeRepository employeeRepository,  Scanner scanner) {

        // input and checking Employee ID
        String inputEmployeeId = UserInputItemView.inputItemWithValidation(
                "Geben Sie die Mitarbeiter-ID ein.",
                "Ganze Zahl",
                UserInputValidator::validatorEmployee,
                employeeRepository, scanner);
        if (inputEmployeeId == null) {
            return null;
        }


        // input and checking first name
        String inputFirstName = inputItem("Geben Sie den Vornamen des Mitarbeiters ein.",
                "Anna, Anna Mari, Anna-Mari", UserInputValidator::isName,  scanner);
        if (inputFirstName == null) {
            return null;
        }


        // input and checking last name
        String inputLastName = inputItem("Geben Sie den Nachnamen des Mitarbeiters ein.",
                "Schmidt, Schmidt-Bauer, Von Trapp", UserInputValidator::isName,  scanner);
        if (inputLastName == null) {
            return null;
        }


        // input and checking position
        String inputPosition = inputItem("Geben Sie die Position des Mitarbeiters ein.",
                "Fahrer, Fahrzeug Disponent, Flotten-Manager", UserInputValidator::isName,  scanner);
        if (inputPosition == null) {
            return null;
        }


        // input and checking Insurance Number
        String inputInsuranceNumber = inputItemWithValidation(
                "Geben Sie die Sozialversicherungsnummer des Mitarbeiters ein.",
                "Eine Zahl mit 10 Ziffern",
                UserInputValidator::validatorInsuranceNumber,
                employeeRepository,  scanner);
        if (inputInsuranceNumber == null) {
            return null;
        }


        // input and checking BIRTHDAY
        String inputBirthday = inputItem("Geben Sie das Geburtsdatum des Mitarbeiters ein.",
                "dd.mm.yyyy", UserInputValidator::isBirthday,  scanner);
        if (inputBirthday == null) {
            return null;
        }


        // input and checking Street
        String inputStreet = inputItem("Geben Sie den Straßennamen ein.",
                "Zollergasse, Peter-Wagner-Straße, Peter Wagner Str.", UserInputValidator::isNameWithPoint,  scanner);
        if (inputStreet == null) {
            return null;
        }


        // input and checking house number
        String inputHouseNumber = inputItem("Geben Sie die Hausnummer ein",
                "55, 55a, 55a\\1", UserInputValidator::isHouseNumber,  scanner);
        if (inputHouseNumber == null) {
            return null;
        }


        // input and checking post code
        String inputPostCode = inputItem("Geben Sie die Postleitzahl ein",
                "Eine Zahl mit 4 Ziffern", UserInputValidator::isAustrianPostCode,  scanner);
        if (inputPostCode == null) {
            return null;
        }



        // input and checking city
        String inputCity = inputItem("Geben Sie die Stadt ein",
                "Graz, St. Veit, Bad-Aussee", UserInputValidator::isNameWithPoint,  scanner);
        if (inputCity == null) {
            return null;
        }

        boolean inputFleetAccess;

        inputFleetAccess = ChoiceMenuView.askYesNo("Hat der Mitarbeiter Zugang zum Führen von Fahrzeugen?", scanner);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return new Employee(
                Integer.parseInt(inputEmployeeId),
                inputFirstName,
                inputLastName,
                inputPosition,
                inputInsuranceNumber,
                LocalDate.parse(inputBirthday, formatter),
                inputStreet,
                inputHouseNumber,
                inputPostCode,
                inputCity,
                inputFleetAccess);
    }
}
