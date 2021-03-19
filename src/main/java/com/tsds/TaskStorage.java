package com.tsds;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

@Component
public class TaskStorage {
    private List<Task> tasks = new ArrayList<>();

    public TaskStorage() {
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(String text) {
        tasks.add(new Task(tasks.size() + 1, text));
        save();
    }

    public boolean deleteTask(int id) {
        boolean ok = tasks.removeIf(task -> task.getId() == id);
        if (ok) {
            save();
        }
        return ok;
    }

    public boolean setComplete(int id, boolean complete) {
        Optional<Task> found = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (found.isEmpty()) {
            return false;
        }
        found.get().setCompleted(complete);
        save();
        return true;
    }

    @PostConstruct
    public void addInitialTasks() {
        try {
            boolean load = load();
            if (!load) {
                addTask("Learn Java");
                addTask("Learn SQL");
                addTask("Profit!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void save() {
        try {
            boolean dir = new File("data").mkdir();
            if (dir) {
                System.out.println("Created data directory");
            }
            new ObjectMapper().writeValue(new File("data/tasks.json"), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean load() throws IOException {
        File file = new File("data/tasks.json");
        if (file.exists()) {
            TaskStorage ts = new ObjectMapper().readValue(file, TaskStorage.class);
            tasks = ts.tasks;
            return true;
        }
        return false;
    }
}
