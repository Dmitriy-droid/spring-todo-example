package com.tsds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
        String browserUrl = System.getenv("BROWSER_OPEN_URL");
        if (browserUrl != null) {
            new BrowserOpener().goToUrl(browserUrl);
        }
    }
}
