package model.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private final String url;
    private final String username;
    private final String password;
    private static Connection connection = null;

    //Constructor gets the information from DBConfigLoader
    public DatabaseConnector(DatabaseConfigurator configurator) {

        Properties dbConnectionProperties = configurator.getDBConnectionProperties();

        this.url = dbConnectionProperties.getProperty("db.url");
        this.username = dbConnectionProperties.getProperty("db.username");
        this.password = dbConnectionProperties.getProperty("db.password");
    }

    //Method returns connection, if connection is not open, it opens it
    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url,username,password);
            }
        }

        catch (SQLException e) {
            System.out.println("Verbindung mit Datenbank kann nicht hergestellt werden.\n"+
                    "Bitte überprüfen Sie die Einstellungen um das Programm zu verwenden");
            System.exit(0);
        }
        return connection;
    }

    //Method closes open connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Schließen der Verbindung!");
        }
    }
}
