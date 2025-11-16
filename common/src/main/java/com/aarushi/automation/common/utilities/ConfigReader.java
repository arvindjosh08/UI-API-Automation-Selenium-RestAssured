package com.aarushi.automation.common.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();
    private static boolean isLoaded = false;
    protected static final Logger logger = LogManager.getLogger(ConfigReader.class);


    /**
     * Loads the configuration file based on the environment.
     * Environment is passed as a JVM argument: -Denvironment=qa
     * Default = dev
     */
    public static synchronized void loadConfig() {
        if (isLoaded) return;

        String environment = System.getProperty("environment", "sit").toLowerCase();
        String resourcePath = "configuration/config-" + environment + ".properties";

        // Load using the module's class loader
        try (InputStream fis = ConfigReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (fis == null) {
                throw new RuntimeException("Config file not found in classpath: " + resourcePath);
            }
            properties.load(fis);
            isLoaded = true;
            System.out.println("Loaded configuration for environment: " + environment);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file: " + resourcePath, e);
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

    public static String getDataFilePath(String fileName) {
        String basePath = System.getProperty("user.dir");
        System.out.println(basePath);
        String environment = System.getProperty("environment", "sit").toLowerCase();
        return Paths.get(basePath, "src", "test", "resources", "testdata", environment, fileName).toString();
    }
}