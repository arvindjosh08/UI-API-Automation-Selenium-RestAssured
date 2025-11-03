package com.aarushi.automation.ui.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();
    private static boolean isLoaded = false;

    /**
     * Loads the configuration file based on the environment.
     * Environment is passed as a JVM argument: -Denvironment=qa
     * Default = dev
     */
    public static synchronized void loadConfig() {
        if (isLoaded) {
            return; // load only once per JVM
        }
        String environment = System.getProperty("environment", "sit").toLowerCase();
        String basePath = System.getProperty("user.dir");
        String configFilePath = Paths.get(basePath, "src", "test", "resources","ui","configuration",
                        String.format("config-%s.properties", environment))
                .toString();

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
            isLoaded = true;
            System.out.println("Loaded configuration for environment: " + environment);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + configFilePath, e);
        }
    }

    /**
     * Returns the value for a given key.
     */
    public static String get(String key) {
        if (!isLoaded) {
            loadConfig();
        }
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing key in config: " + key);
        }
        return value.trim();
    }

    /**
     * Returns the value as integer (for numeric configs like timeouts).
     */
    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    /**
     * Returns the value as boolean (for flags like headless mode).
     */
    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}