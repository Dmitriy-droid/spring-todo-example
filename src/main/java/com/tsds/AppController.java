package com.tsds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class AppController {
    private final TaskStorage taskStorage;

    @Autowired
    public AppController(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    @GetMapping("/tasks")
    public List<Task> listTasks() {
        return taskStorage.getTasks();
    }


    @PostMapping("/tasks")
    public AppResponse addTask(@RequestParam String text) throws SQLException {
        taskStorage.addTask(text);
        return AppResponse.ok();
    }

    @DeleteMapping("/tasks")
    public AppResponse deleteTask(@RequestParam int id) {
        return taskStorage.deleteTask(id) ? AppResponse.ok() : AppResponse.error("Task not found");
    }

    @PostMapping("/tasks/complete")
    public AppResponse setComplete(@RequestParam int id, @RequestParam boolean value) {
        return taskStorage.setComplete(id, value) ? AppResponse.ok() : AppResponse.error("Task not found");
    }
}
