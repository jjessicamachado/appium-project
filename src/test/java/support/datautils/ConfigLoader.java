package support.datautils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static Properties config;

    private static Properties getConfig() {
        if (config == null) {
            new ConfigLoader();
        }
        return config;
    }

    private ConfigLoader() {
        config = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            config.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        return systemValue != null ? systemValue : getConfig().getProperty(key);
    }

    public static void setProperty(String key, String value) {
        getConfig().setProperty(key, value);
    }
}
