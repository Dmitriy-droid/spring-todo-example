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
    private int maxId = 0;
    private List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(String text) {
        maxId++;
        tasks.add(new Task(maxId, text));

    }

    public boolean deleteTask(int id) {
        boolean ok = tasks.removeIf(task -> task.getId() == id);
        return ok;
    }

    public boolean setComplete(int id, boolean complete) {
        Optional<Task> found = tasks.stream().filter(task -> task.getId() == id).findFirst();
        if (found.isEmpty()) {
            return false;
        }
        found.get().setCompleted(complete);

        return true;
    }
}
