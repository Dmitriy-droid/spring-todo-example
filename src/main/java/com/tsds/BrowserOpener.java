package com.tsds;

import java.io.IOException;

public class BrowserOpener {
    public void goToUrl(String url) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            goToUrlMac(url);
        } else if (os.contains("win")) {
            goToUrlWin(url);
        } else {
            goToUrlLinux(url);
        }
    }

    private void goToUrlMac(String url) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("open " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToUrlWin(String url) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goToUrlLinux(String url) {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("xdg-open " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
