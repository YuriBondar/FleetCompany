package model.validator;



import model.dataAccess.EmployeeRepository;
import model.dataAccess.FleetRepository;
import model.enteties.employee.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInputValidator {

    //check if String is int in some range

    public static boolean isIntInRange(String userChoice, int firstInRange, int lastInRange) {

        if (!isInt(userChoice)) {
            return false;
        }

        int userChoiceInt = Integer.parseInt(userChoice);

        if (firstInRange <= userChoiceInt && userChoiceInt <= lastInRange) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe. Geben Sie eine Nummer zwischen " + firstInRange +
                    " und " + lastInRange + " ein.");
            return false;
        }
    }

    public static boolean isIntMoreThan0(String userChoice) {
        if (!isInt(userChoice)) {
            return false;
        }

        int userChoiceInt = Integer.parseInt(userChoice);
        if (userChoiceInt > 0) {
            return true;
        } else {
            System.out.println("Ungültige Eingabe. Geben Sie eine Zahl größer als 0 ein.");
            return false;
        }
    }

    //check if String is int
    //if not print special error message
    public static boolean isInt(String input) {

        try {
            Integer.parseInt(input);
            return true;
        }

        catch (Exception e){
            System.out.println("Ungültige Eingabe. Geben Sie eine ganze Zahl ein.");
            return false;
        }
    }

    // check name
    // one or some words, "-" also possible, first letter of each word is big
    public static boolean isName(String input) {

        String pattern = "^[A-ZÖÄÜ][a-zA-ZöäüßÖÄÜ]*(\\s|-)?([A-ZÖÄÜ][a-zA-ZöäüßÖÄÜ]*)*$";

        if (input.length() > 50){
            System.out.println("Das Wort darf nicht mehr als 50 Zeichen enthalten.");
            return false;
        }

        if (input.matches(pattern)) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe.\n" +
                    "Die Eingabe muss mit einem Großbuchstaben beginnen und Buchstaben,\n" +
                    "Leerzeichen oder das Zeichen '-' enthalten.");
            return false;
        }
    }

    // Method for checking city names and street names

    // - Can consist of one or more words
    // - Words can be separated by a space (" ") or a hyphen ("-")

    // First word:
    // - Starts with a capital letter
    // - Must have at least one letter or more
    // - Can end with a "."

    // Subsequent words:
    // - Start with a capital letter
    // - Must have at least one letter or more
    // - Can end with a "."
    // - Are optional

    public static boolean isNameWithPoint(String input) {

        String pattern = "^[A-ZÖÄÜ][a-zA-ZöäüßÖÄÜ]*\\.?((\\s|-)([A-ZÖÄÜ][a-zA-ZöäüßÖÄÜ]*)\\.?)*$";

        if (input.length() > 50){
            System.out.println("Das Wort darf nicht mehr als 50 Zeichen enthalten.");
            return false;
        }

        if (input.matches(pattern)) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe.\n" +
                    "Die Eingabe muss mit einem Großbuchstaben beginnen und Buchstaben,\n" +
                    "Leerzeichen, Punkte oder das Zeichen '-' enthalten.");
            return false;
        }
    }



    // check model auto
    // one or some words which could include numbers, "-" also possible

    public static boolean isVehicleModel(String input) {

        String pattern = "^[a-zA-ZöäüßÖÄÜ\\d]+((\\s|-)?([a-zA-ZöäüßÖÄÜ\\d]+))*$";

        if (input.length() > 50){
            System.out.println("Das Wort darf nicht mehr als 50 Zeichen enthalten.");
            return false;
        }

        if (input.matches(pattern)) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe.\n" +
                    "Die Eingabe muss mit einem Großbuchstaben beginnen und Buchstaben, Zahlen,\n" +
                    "Leerzeichen oder das Zeichen '-' enthalten.");
            return false;
        }
    }

    // check Socialversicherungnummer
    public static boolean isAustriaSocialInsuranceNumber(String input) {
        String pattern = "^[0-9]{10}$";
        if (input.matches(pattern)) {
            return true;
        }
        else {
            System.out.println("Ungültige Eingabe. Geben Sie eine Zahl mit genau zehn Ziffern ein.");
            return false;
        }
    }

    // check License Plate in Austria
    public static boolean isLicensePlate(String input){
        String pattern = "^[A-ZÖÄÜ]{1,2}\\d{1,4}[A-Z]{1,3}$";
        if (input.matches(pattern)) {
            return true;
        }
        else {
            System.out.println("Ungültige Eingabe. Geben Sie die Daten im Format A999AA oder AA999AA ein.");
            return false;
        }
    }

    public static boolean isDate(String input){
        String pattern = "^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$";
        if(input.matches(pattern)){
            String[] inputArray = input.split("\\.");
            if ((1950 < Integer.parseInt(inputArray[2])) && (Integer.parseInt(inputArray[2]) < 2100)
                    && Integer.parseInt(inputArray[0]) <= 31 && Integer.parseInt(inputArray[1]) <= 12){
                return true;
            }
            else {
                System.out.println("Ungültige Eingabe. Ungültiges Jahr, Monat oder Datum.");
                return false;
            }
        }
        else {
            System.out.println("Ungültige Eingabe. Geben Sie die Daten im Format dd-mm-yyyy ein.");
            return false;
        }
    }

    // check birthday in format dd.MM.yyyy
    // check is date for person not younger than 17 and not older then 74
    public static boolean isBirthday(String input){
        String pattern = "^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$";

        if(input.matches(pattern)){
            String[] inputArray = input.split("\\.");
            if ((1950 < Integer.parseInt(inputArray[2])) && (Integer.parseInt(inputArray[2]) < (LocalDate.now().getYear())-16)
                    && Integer.parseInt(inputArray[0]) <= 31 && Integer.parseInt(inputArray[1]) <= 12){
                return true;
            }
            else {
                System.out.println("Ungültige Eingabe. Ungültiges Jahr, Monat oder Datum.");
                return false;}
        }
        else {
            System.out.println("Ungültige Eingabe. Geben Sie die Daten im Format dd-mm-yyyy ein.");
            return false;
        }
    }

    public static boolean isHouseNumber(String input) {
        String pattern = "^[0-9]+[a-zA-Z]?(\\\\d+)?$";

        if (input.matches(pattern)) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe. Geben Sie die Daten im Format 55, 55a, 55a\\1 ein.");
            return false;
        }
    }

    // check post code in Austria
    public static boolean isAustrianPostCode(String input) {
        String pattern = "^[0-9]{4}$";

        if (input.matches(pattern)) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe. Geben Sie eine Zahl mit genau zehn Ziffern ein.");
            return false;
        }
    }

    // check Diesel or Benzin
    public static boolean isFuelType(String input){

        if (input.equals("Benzin") || input.equals("Diesel")) {
            return true;
        }

        else {
            System.out.println("Ungültige Eingabe. Geben Sie Benzin oder Diesel ein.");
            return false;
        }
    }

    // check seats number between 0 and 7
    public static boolean isSeatsNumber(String input) {

        return isIntInRange(input, 1, 7);
    }




    // check int with 6 digits
    public static boolean isNatCode(String input) {
        String pattern = "^[0-9]{6}$";
        if (input.matches(pattern)) {
            return true;
        } else {
            System.out.println("Ungültige Eingabe. Geben Sie eine ganze Zahl mit 6 Ziffern ein.");
            return false;
        }
    }


    //check format RF-XXX
    public static boolean isInvNumber(String input) {
        String pattern = "^FR-[0-9]{3}$";
        if (input.matches(pattern)) {
            return true;
        } else {
            System.out.println("Ungültige Eingabe. Geben Sie die Daten im Format RF-XXX ein.");
            return false;
        }
    }


    // check if is this vehicle in Database
    public static boolean validatorMotorVehicle(String input, FleetRepository databaseRepository) {

        if(!isNatCode(input))
            return false;

        ArrayList<String> vehicleIDS = databaseRepository.retrieveVehicleIDs();
        for (String vehicleID : vehicleIDS) {
            if (input.equals(vehicleID)) {
                System.out.println("Dieses Fahrzeug ist bereits in der Datenbank vorhanden.");
                return false;
            }
        }
        return true;
    }

    public static boolean validatorBike(String input, FleetRepository databaseRepository) {

        if(!isInvNumber(input))
            return false;

        ArrayList<String> vehicleIDS = databaseRepository.retrieveVehicleIDs();
        for (String vehicleID : vehicleIDS) {
            if (input.equals(vehicleID)) {
                System.out.println("Dieses Fahrzeug ist bereits in der Datenbank vorhanden.");
                return false;
            }
        }
        return true;
    }
    // check if is this Insurance Numbers in Database
    public static boolean validatorInsuranceNumber(String input, EmployeeRepository databaseRepository) {

        ArrayList<Employee> employees = databaseRepository.selectEmployeesFromTable();
        for (Employee employee : employees) {
            if (input.equals(employee.getSocialsecuritynumber())){
                System.out.println("Ein Mitarbeiter mit dieser Sozialversicherungsnummer ist bereits " +
                        "in der Datenbank vorhanden");
                return false;
            }
        }
        return true;
    }

    // check if is this employee in Database
    public static boolean validatorEmployee(String input, EmployeeRepository databaseRepository) {

        ArrayList<Employee> employees = databaseRepository.selectEmployeesFromTable();
        for (Employee employee : employees) {
            if (Integer.parseInt(input) == employee.getEmployeeID()){
                System.out.println("Ein Mitarbeiter mit dieser ID ist bereits in der Datenbank vorhanden.");
                return false;
            }
        }
        return true;
    }


    public static boolean isYesNo(String s, Scanner scanner){
        System.out.println(s);
        while (true) {
            System.out.println("Drücken Sie y/n");
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                if (input.equals("y")) {
                    return true;
                } else if (input.equals("n")) {
                    return false;
                } else {
                    System.out.println("Ungültige Eingabe.");
                }
            } else {
                return false;
            }
        }
    }


    public static boolean validatorMileage(int mileage, String newMileage) {
        if(mileage <= Integer.parseInt(newMileage)){
            return true;
        } else {
            System.out.println("Der neue Kilometerstand muss den aktuellen überschreiten.");
            System.out.println("Der  aktuelle Kilometerstand ist: " + mileage);
            return false;
        }
    }


}
