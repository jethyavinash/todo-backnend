package com.todo.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timeStamp;

    public ErrorDetails() {
    }

    public ErrorDetails(String message, String details, LocalDateTime timeStamp) {
        this.message = message;
        this.details = details;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
