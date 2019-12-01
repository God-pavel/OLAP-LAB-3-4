package com.olap.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolHandler {
    private static HikariConfig config = new HikariConfig("./src/main/resources/database.properties");
    private static HikariDataSource dataSource = new HikariDataSource(config);

    private ConnectionPoolHandler() {}

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection not resolved");
        }
    }
}
