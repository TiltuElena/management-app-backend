package com.example.demo.util.exception;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<Object> handleException(RuntimeException e) {
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ExceptionMessageDTO(e.getMessage()));
    }
}
