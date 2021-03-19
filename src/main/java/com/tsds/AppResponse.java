package com.tsds;

import com.fasterxml.jackson.annotation.JsonInclude;

enum Result {
    ok, error
}

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse {
    private final Result result;
    private final String error;

    public Result getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public AppResponse(Result result, String error) {
        this.result = result;
        this.error = error;
    }

    public static AppResponse ok() {
        return new AppResponse(Result.ok, null);
    }

    public static AppResponse error(String message) {
        return new AppResponse(Result.error, message);
    }
}
