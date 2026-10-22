package com.example.nikitasergeevfinalfx.jdbc;

import org.h2.tools.RunScript;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseInitializer {
    private final AppConfig config;

    public DatabaseInitializer(AppConfig config) {
        this.config = config;
    }

    public void init() {
        try (Connection connection = DriverManager.getConnection(
                config.getUrl(), config.getUsername(), config.getPassword())) {

            if (isTableEmpty(connection)) {
                runSqlScript(connection);
                System.out.println("БД заполнена данными");
            } else {
                System.out.println("Данные уже есть");
            }

        } catch (Exception e) {
            throw new RuntimeException("Ошибка инициализации БД", e);
        }
    }

    private boolean isTableEmpty(Connection conn) throws SQLException {
        try (ResultSet resultSet = conn.createStatement().executeQuery(
                "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES " +
                        "WHERE TABLE_NAME = 'PROFILE_CARD'")) {
            resultSet.next();

            if (resultSet.getInt(1) == 0) {
                return true;
            }
        }

        try (ResultSet resultSet = conn.createStatement()
                .executeQuery("SELECT COUNT(*) FROM profile_card")) {
            resultSet.next();

            return resultSet.getInt(1) == 0;
        }
    }

    private void runSqlScript(Connection conn) throws Exception {
        InputStream stream = getClass().getClassLoader()
                .getResourceAsStream(config.getInitScript());

        if (stream == null) {
            throw new RuntimeException(
                    "SQL script not found in classpath: [" + config.getInitScript() + "]");
        }

        try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            RunScript.execute(conn, reader);
        }
    }
}