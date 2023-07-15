package com.example.silkpaytestassignment.controller;

import com.example.silkpaytestassignment.dto.ErrorResponse;
import com.example.silkpaytestassignment.exeption.InsufficientFundsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException(InsufficientFundsException e) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }
}
