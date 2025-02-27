package views;

import model.enteties.employee.Employee;
import model.validator.UserInputValidator;
import views.inputViews.UserInputItemView;

import java.util.Scanner;

public class UpdateEmplyeeDialogView {

    public static Employee UpdateEmployee(Employee chosenEmployee, Scanner scanner)
    {
        int changeChoice;

        while (true) {
            changeChoice = ChoiceMenuView.BuildChoiceMenu(
                    "Welchen Wert wollen Sie ändern?",
                    Employee.getEmployeeAttributesNamesGermanForUpdate(),
                    scanner);

            switch (changeChoice) {
                case 1:
                    chosenEmployee.setFirstname(UserInputItemView.inputItemNoExit(
                            "Geben Sie den Vornamen des Mitarbeiters ein", "",
                            UserInputValidator::isName, scanner));
                    break;
                case 2:
                    chosenEmployee.setLastname(UserInputItemView.inputItemNoExit(
                            "Geben Sie den Nachnamen des Mitarbeiters ein", "",
                            UserInputValidator::isName, scanner));
                    break;
                case 3:
                    chosenEmployee.setPosition(UserInputItemView.inputItemNoExit(
                            "Geben Sie die Position des Mitarbeiters ein",
                            "Fahrer, Fahrzeug Disponent, Flotten-Manager",
                            UserInputValidator::isName, scanner));
                    break;
                case 4:
                    chosenEmployee.setStreetname(UserInputItemView.inputItemNoExit(
                            "Geben Sie den Straßennamen ein",
                            "Zollergasse, Peter-Wagner-Straße, Peter Wagner Str.",
                            UserInputValidator::isNameWithPoint, scanner));
                    break;
                case 5:
                    chosenEmployee.setHouseNr(UserInputItemView.inputItemNoExit(
                            "Geben Sie die Hausnummer ein", "55, 55a, 55a\\1",
                            UserInputValidator::isHouseNumber, scanner));
                    break;
                case 6:
                    chosenEmployee.setPostcode(UserInputItemView.inputItemNoExit(
                            "Geben Sie die Postleitzahl ein", "",
                            UserInputValidator::isAustrianPostCode, scanner));
                    break;
                case 7:
                    chosenEmployee.setPostcode(UserInputItemView.inputItemNoExit(
                            "Geben Sie einen Ort ein", "",
                            UserInputValidator::isNameWithPoint, scanner));
                    break;
            }
            boolean doNextUpdate = ChoiceMenuView.askYesNo(
                    "Die anderen Daten für diesen Mitarbeiter aktualisieren??", scanner);
            if (!doNextUpdate)
                break;
        }
        return chosenEmployee;
    }
}
