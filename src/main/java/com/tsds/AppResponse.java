package com.tsds;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse {
    private final String result;
    private final String error;

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public AppResponse(String result, String error) {
        this.result = result;
        this.error = error;
    }

    public static AppResponse ok() {
        return new AppResponse("ok", null);
    }

    public static AppResponse error(String message) {
        return new AppResponse("error", message);
    }
}
