package model.enteties.fleet.vehicleMaintenance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record VehicleService(String vehicleID, LocalDate serviceDate, int mileage) {
    @Override
    public String toString() {
        if (vehicleID.contains("FR")) {
            return String.format("%-20s",
                    serviceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } else {
            return String.format("%-20s%-20s",
                    serviceDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    mileage);
        }
    }

    public static String toStringAttributesNamesFormat() {
        return String.format(
                "%-20s%-20s",
                "Datum",
                "Kilometerstand");
    }
}
