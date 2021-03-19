package com.tsds;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FileStructure {
    public List<Task> tasks;

    public FileStructure(List<Task> tasks) {
        this.tasks = tasks;
    }

    public FileStructure() {
    }
}

public class TaskStorage {
    private List<Task> tasks = new ArrayList<>();

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

    public void addInitialTasks() {
        boolean load = false;
        try {
            load = load();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        if (!load) {
            addTask("Learn Java");
            addTask("Learn SQL");
            addTask("Profit!");
        }
    }

    public void save() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FileStructure fs = new FileStructure(tasks);
            File data = new File("data");
            if (!data.exists()) {
                data.mkdir();
            }
            objectMapper.writeValue(new File("data/tasks.json"), fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean load() throws IOException {
        File file = new File("data/tasks.json");
        if (file.exists()) {
            ObjectMapper objectMapper = new ObjectMapper();
            FileStructure fs = objectMapper.readValue(file, FileStructure.class);
            tasks = fs.tasks;
            return true;
        }
        return false;
    }
}
