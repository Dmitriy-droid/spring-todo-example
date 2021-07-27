package com.tsds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
        String browserUrl = System.getenv("BROWSER_OPEN_URL");
        if (browserUrl != null) {
            new BrowserOpener().goToUrl(browserUrl);
        }

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "dim";
        String password = "mypassword";

        String query = "SELECT * FROM save";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setCompleted(resultSet.getBoolean(3));

                System.out.println(task);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
