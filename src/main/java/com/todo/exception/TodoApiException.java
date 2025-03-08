package com.todo.exception;

import org.springframework.http.HttpStatus;

public class TodoApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public TodoApiException(String message,HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
