package com.tsds;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope("singleton")
    public TaskStorage taskStorage() {
        TaskStorage storage = new TaskStorage();
        storage.addInitialTasks();
        return storage;
    }
}
