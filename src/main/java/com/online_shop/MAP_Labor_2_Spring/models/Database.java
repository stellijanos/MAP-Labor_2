package com.online_shop.MAP_Labor_2_Spring.models;

import com.online_shop.MAP_Labor_2_Spring.repositories.Env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String dbServer, dbUsername, dbPassword, dbName;

    public void loadCredentials() {
        dbServer = Env.load().get("DB_SERVERNAME");
        dbUsername = Env.load().get("DB_USERNAME");
        dbPassword = Env.load().get("DB_PASSWORD");
        dbName = Env.load().get("DB_NAME");
    }

    public Connection conn() {
        loadCredentials();

        String url = "jdbc:mysql://" + dbServer + ":3306/" + dbName;

        try {
            return DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException e) {
            return null;
        }
    }
}
