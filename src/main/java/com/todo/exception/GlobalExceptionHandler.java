package com.todo.exception;

import com.todo.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoApiException.class)
    public ResponseEntity<ErrorDetails> handleTodoApiException(TodoApiException todoApiException, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(todoApiException.getMessage(),webRequest.getDescription(false),LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
