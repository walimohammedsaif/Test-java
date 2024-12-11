package io.fastpix;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FastPixConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = FastPixConfigLoader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new IOException("Unable to find fastpix.properties");
            }

            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Error loading configuration: " + ex.getMessage());
            throw new RuntimeException("Could not load FastPix configuration", ex);
        }
    }

    /**
     * Retrieve a property from the configuration
     * @param key Property key
     * @return Property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Retrieve a property with a default value
     * @param key Property key
     * @param defaultValue Default value if key not found
     * @return Property value or default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}