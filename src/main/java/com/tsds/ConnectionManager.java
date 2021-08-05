package com.tsds;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
