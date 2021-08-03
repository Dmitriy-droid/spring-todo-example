package com.tsds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class App {
    public static void main(final String[] args) {

        SpringApplication.run(App.class, args);
        ConnectionManager.connect();
    }
}