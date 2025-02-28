package views;

import model.enteties.DTOs.FilterWithAttributeDTO;
import model.enteties.employee.Employee;
import model.enteties.employee.EmployeeAttributes;
import model.enteties.fleet.vehicle.attributes.VehicleType;
import model.validator.UserInputValidator;
import views.inputViews.UserInputItemView;

import java.util.HashMap;
import java.util.Scanner;

import java.util.ArrayList;

public class ChoiceEmployeeFilterDialog {

    public static FilterWithAttributeDTO choseFilter(Scanner scanner){
        String[] filterChoiceList = {
                "Vorname",
                "Nachname",
                "Position",
                "Postleitzahl",
                "Ort",
                "Fuhrpark-Zugriff"};

        FilterWithAttributeDTO filter = new FilterWithAttributeDTO();

        int filterChoice = ChoiceMenuView.BuildChoiceMenu(
                "Nach welchem Wert wollen Sie filtern?", filterChoiceList, scanner);


        switch (filterChoice) {
            case 1:
                String inputFirstName = UserInputItemView.inputItemNoExit(
                        "Geben Sie einen Vornamen ein",
                        "",
                        UserInputValidator::isName,
                        scanner);
                filter.setFilter(inputFirstName);
                filter.setEmployeeAttribute(EmployeeAttributes.FIRSTNAME);
                break;
            case 2:
                String inputLastName = UserInputItemView.inputItemNoExit("Geben Sie einen Nachnamen ein",
                        "",
                        UserInputValidator::isName,
                        scanner);
                filter.setFilter(inputLastName);
                filter.setEmployeeAttribute(EmployeeAttributes.LASTNAME);
                break;
            case 3:
                String inputPosition = UserInputItemView.inputItemNoExit("Geben Sie eine Position ein",
                        "",
                        UserInputValidator::isName,
                        scanner);
                //adding the Strings to objectList
                filter.setFilter(inputPosition);
                filter.setEmployeeAttribute(EmployeeAttributes.POSITION);
                break;
            case 4:
                String inputPosCode = UserInputItemView.inputItemNoExit("Geben Sie eine Postleitzahl ein",
                        "",
                        UserInputValidator::isAustrianPostCode,
                        scanner);
                filter.setFilter(inputPosCode);
                filter.setEmployeeAttribute(EmployeeAttributes.POSTCODE);
                break;
            case 5:
                String inputCity = UserInputItemView.inputItemNoExit("Geben Sie einen Ort ein",
                        "",
                        UserInputValidator::isNameWithPoint,
                        scanner);
                //adding the Strings to objectList
                filter.setFilter(inputCity);
                filter.setEmployeeAttribute(EmployeeAttributes.CITY);
                break;
            case 6:
                boolean inputFleetAccess = ChoiceMenuView.askYesNo(
                    "Wollen Sie die Mitarbeiter mit Fuhrpark-Zugriff herausfiltern?", scanner);
                if (inputFleetAccess) {
                    filter.setFilter("true");
                    filter.setEmployeeAttribute(EmployeeAttributes.FLEETACCESS);
                }
                break;

        }
        return filter;
    }
}
