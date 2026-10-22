package com.example.nikitasergeevfinalfx.jdbc;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private final Properties properties = new Properties();

    public AppConfig() {
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (inputStream == null) {
                throw new RuntimeException("application.properties not found");
            }

            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public String getUrl() {
        return properties.getProperty("jdbc.url");
    }

    public String getUsername() {
        return properties.getProperty("jdbc.username");
    }

    public String getPassword() {
        return properties.getProperty("jdbc.password", "");
    }

    public String getInitScript() {
        return properties.getProperty("jdbc.initScript");
    }
}