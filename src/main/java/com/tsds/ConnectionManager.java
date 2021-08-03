package com.tsds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectionManager {

    public static Connection connection;

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "dim";
    private static String password = "mypassword";

    public static void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
