# 1. Project Description

A console application for managing a vehicle fleet and the human resources of a company.

# 2. Technologies

The project is developed using the following technologies:

- **Java**: Oracle OpenJDK 23
- **Database**: MySQL 8.0
- **Database Connection**: JDBC (MySQL Connector)

# 3. Detailed Project Description
## Fleet
The vehicle fleet can consist of four types of transport:

- Passenger car
- Truck
- Motorcycle
- Bicycle

The user has the ability to:

- Add a vehicle
- Remove a vehicle
- Display all vehicles or a specific type of vehicle
- Update the mileage
- Update the amount of fuel used by a vehicle

### Driver Assignments:

The user has the ability to set a driver assignment for a vehicle.

Assignment properties:

- Vehicle (ID)
- Driver (ID)
- Start date
- End date

Each vehicle can have only one assigned driver at a time.
A single driver can be assigned to multiple vehicles.

If a new driver is assigned to a vehicle, the previous assignment is closed and stored in the archive.

### Vehicle Service:

The user has the ability to:

- Check the Vehicle Service status. 

Based on the service interval, the user receives information on how much time remains until the next service or how overdue the next service is.

- Update the Vehicle Service status.

## Employee
The user has the ability to:

- Add an employee
- Display an employee (full list or filtered list)
- Remove an employee
- Update employee data


## Database
Script for Database Creation: database.txt




