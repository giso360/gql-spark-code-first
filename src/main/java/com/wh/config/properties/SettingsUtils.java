package com.wh.config.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class allows to use parameter/value pairs declared in app.properties
 */
public class SettingsUtils {

    private final Properties PROPERTIES = new Properties();

    public SettingsUtils() {
        loadProperties("config/app.properties");
    }

    private void loadProperties(final String filePath) {
        try (InputStream input = SettingsUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            if ( input != null ) {
                PROPERTIES.load(input);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProps() {
        return PROPERTIES;
    }
}
