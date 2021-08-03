package com.tsds;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

@Component
public class TaskStorage {
    private int maxId = 0;
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {

        List<Task> result = new ArrayList<>();

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "SELECT * FROM tasks";

        try {
            Statement statement = connectionManager.getConnection().createStatement();
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
        maxId++;
        tasks.add(new Task(maxId, text));

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "INSERT INTO tasks (id, name, completed) VALUES (?,?,?)";

        try {
            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);
            statement.setInt(1, maxId);
            statement.setString(2, text);
            statement.setBoolean(3,false);
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean deleteTask(int id) {
        boolean ok = tasks.removeIf(task -> task.getId() == id);

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "DELETE FROM tasks WHERE id=?";

        try {
            PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ok;
    }


    public boolean setComplete(int id, boolean complete) {
        Optional<Task> found = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (found.isEmpty()) {
            return false;
        }
        found.get().setCompleted(complete);

        ConnectionManager connectionManager = new ConnectionManager();

        String query = "update tasks set completed = true and completed = false where id = ?";

                try {
                    PreparedStatement statement = connectionManager.getConnection().prepareStatement(query);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
        return true;
    }
}
