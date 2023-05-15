package Weather_app.Weathers_app;

import java.io.*;
import java.util.Properties;

public class UserConfigs {
    public String getUnits() {
        File configFile = new File("config_settings.properties");
        String units = "metric";
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            units = props.getProperty("units");   //Not the one in src

            if (units.toLowerCase() != "metric" || units.toLowerCase() != "imperial" || units.toLowerCase() != "uk") {
                units = "metric";
            }

            System.out.print("Units name is: " + units);
            reader.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
        return units;
    }
    public void setUnits(String units) {
        File configFile = new File("config_settings.properties");
        try {
            Properties props = new Properties();
            props.setProperty("units", units);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Can choose units");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
}
