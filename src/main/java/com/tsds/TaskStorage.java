package com.tsds;

import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskStorage {

    public List<Task> getTasks() {

        List<Task> result = new ArrayList<>();



        String query = "SELECT * FROM tasks order by id desc";

        try {
            Statement statement = ConnectionManager.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setName(resultSet.getString(2));
                task.setCompleted(resultSet.getBoolean(3));
                result.add(task);

                System.out.println(task);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void addTask(String text) {

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "INSERT INTO tasks (name) VALUES (?)";

        try {
            PreparedStatement statement = ConnectionManager.connection.prepareStatement(query);
            statement.setString(1, text);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean deleteTask(int id) {

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "DELETE FROM tasks WHERE id=?";

        try {
            PreparedStatement statement = ConnectionManager.connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean setComplete(int id, boolean complete) {

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "update tasks set completed = ? where id = ?";

                try {
                    PreparedStatement statement = ConnectionManager.connection.prepareStatement(query);
                    statement.setBoolean(1, complete);
                    statement.setInt(2, id);
                    statement.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        return true;
    }
}
