package com.livecha.objects;

// this class contains a boolean and a message

public class Result {
    private boolean bool;
    private String message;

    public Result(boolean bool, String message) {
        this.bool = bool;
        this.message = message;
    }

    public boolean isBool() {
        return bool;
    }

    public String getMessage() {
        return message;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
