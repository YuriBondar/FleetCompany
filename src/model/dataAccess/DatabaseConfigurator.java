package model.dataAccess;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfigurator {

    private final Properties connectionProperties = new Properties();

    //Constructor loads the Config-File
    public DatabaseConfigurator() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("DBConfig.properties")) {

            //Meldung, falls die Datei nicht existiert

            if (input==null) {
                System.out.println("Config-Datei DBConfig.properties konnte nicht gefunden werden\n"+
                        "Legen Sie die Datei mit den Zugangsdaten der Datenbank an um das Programm zu verwenden");
                System.exit(0);
            }

            this.connectionProperties.load(input);
        }
        catch (IOException e) {
            System.out.println("Fehler beim laden der Config-Datei");
        }
    }

    //Methode to get specific Properties depending on the given key

    public Properties getDBConnectionProperties() {

        return this.connectionProperties;
    }
}
