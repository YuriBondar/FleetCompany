package model.enteties.employee;


import model.validator.UserInputValidator;
import views.ChoiceMenuView;
import views.inputViews.UserInputItemView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Employee {
    private int employeeID;
    private String firstname;
    private String lastname;
    private String position;
    private String socialsecuritynumber;
    private LocalDate birthday;
    private String streetname;
    private String houseNr;
    private String postcode;
    private String city;
    private boolean fleetAccess;

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSocialsecuritynumber() {
        return socialsecuritynumber;
    }

    public void setSocialsecuritynumber(String socialsecuritynumber) {
        this.socialsecuritynumber = socialsecuritynumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isFleetAccess() {
        return fleetAccess;
    }

    public void setFleetAccess(boolean fleetAccess) {
        this.fleetAccess = fleetAccess;
    }

    public Employee(int employeeID, String firstname, String lastname, String position, String socialsecuritynumber, LocalDate birthday, String streetname, String houseNr, String postcode, String city, boolean fleetAccess) {
        this.employeeID = employeeID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.socialsecuritynumber = socialsecuritynumber;
        this.birthday = birthday;
        this.streetname = streetname;
        this.houseNr = houseNr;
        this.postcode = postcode;
        this.city = city;
        this.fleetAccess = fleetAccess;
    }

    public Employee(HashMap<EmployeeAttributes, String> employee){
        this.employeeID = Integer.parseInt(employee.get(EmployeeAttributes.EMPLOYEEID));
        this.firstname = employee.get(EmployeeAttributes.FIRSTNAME);
        this.lastname = employee.get(EmployeeAttributes.LASTNAME);
        this.position = employee.get(EmployeeAttributes.POSITION);
        this.socialsecuritynumber = employee.get(EmployeeAttributes.SOCIALSECURITYNUMBER);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.birthday = LocalDate.parse(employee.get(EmployeeAttributes.BIRTHDAY), formatter);
        this.streetname = employee.get(EmployeeAttributes.STREETNAME);
        this.houseNr = employee.get(EmployeeAttributes.HOUSENR);
        this.postcode = employee.get(EmployeeAttributes.POSTCODE);
        this.city = employee.get(EmployeeAttributes.CITY);
        this.fleetAccess = employee.get(EmployeeAttributes.FLEETACCESS).equals("yes");
    }


    public String toStringFormat() {
        StringBuilder sb = new StringBuilder();

        String access = isFleetAccess() ? "Ja" : "Nein"; // Упрощенная тернарная операция вместо if-else

        sb.append(String.format("%-20s", getEmployeeID()))
                .append(String.format("%-20s", getFirstname()))
                .append(String.format("%-20s", getLastname()))
                .append(String.format("%-20s", getPosition()))
                .append(String.format("%-20s", getSocialsecuritynumber()))
                .append(String.format("%-20s", getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .append(String.format("%-20s", getStreetname()))
                .append(String.format("%-20s", getHouseNr()))
                .append(String.format("%-20s", getPostcode()))
                .append(String.format("%-20s", getCity()))
                .append(String.format("%-20s", access));

        return sb.toString();
    }


    public String toStringForChoiceMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "ID: " + getEmployeeID()))
                .append(String.format("%-20s", getFirstname()))
                .append(String.format("%-20s", getLastname()));

        return sb.toString();
    }


    public static String toStringAttributeNamesGermanFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s", "ID"))
                .append(String.format("%-20s", "Vorname"))
                .append(String.format("%-20s", "Nachname"))
                .append(String.format("%-20s", "Position"))
                .append(String.format("%-20s", "SVNr."))
                .append(String.format("%-20s", "Geburtstag"))
                .append(String.format("%-20s", "Straße"))
                .append(String.format("%-20s", "Hausnummer"))
                .append(String.format("%-20s", "PLZ"))
                .append(String.format("%-20s", "Ort"))
                .append(String.format("%-20s", "Fuhrpark-Zugriff"));

        return sb.toString();
    }

    public static ArrayList<String> getEmployeeAttributesNamesGermanForUpdate() {
        return new ArrayList<>(Arrays.asList(
                "Vorname",
                "Nachname",
                "Position",
                "Straße",
                "Hausnummer",
                "Postleitzahl",
                "Ort"
        ));
    }

}
