package com.example.nikitasergeevfinalfx;

import com.example.nikitasergeevfinalfx.jdbc.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseUtil(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public DatabaseUtil(AppConfig config) {
        this.url = config.getUrl();
        this.username = config.getUsername();
        this.password = config.getPassword();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}