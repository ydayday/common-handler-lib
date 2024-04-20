package com.ydayday.cmlib.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CmlibErrorResponse> globalRequestException(final Exception ex) {
        ex.printStackTrace();
        return ResponseEntity
                .status(500)
                .body(new CmlibErrorResponse(500, ex.getMessage()));
    }

    // Exception
    @ExceptionHandler(CmlibException.class)
    public ResponseEntity<CmlibErrorResponse> handleGlobalException(CmlibException ex) {
        HttpStatus httpStatus = ex.getStatus();
        CmlibErrorResponse response = CmlibErrorResponse.of(ex);
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CmlibErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        String errorMessage = errors.toString();
        CmlibErrorResponse errorResponse = CmlibErrorResponse.of(HttpStatus.BAD_REQUEST, errorMessage);

        return ResponseEntity.badRequest().body(errorResponse);
    }

}
