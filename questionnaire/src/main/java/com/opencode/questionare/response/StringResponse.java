package com.opencode.questionare.response;

public class StringResponse {

    private String message;

    public StringResponse(String response) {
        this.message = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String response) {
        this.message = response;
    }
}
