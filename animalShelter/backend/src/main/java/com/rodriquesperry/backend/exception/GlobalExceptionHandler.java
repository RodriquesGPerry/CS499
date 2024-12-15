package com.rodriquesperry.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler class to handle application-wide exceptions.
 * This class ensures that any validation errors or exceptions during method argument processing
 * are caught and handled appropriately, providing meaningful error responses to the client.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException, which occurs when validation annotations on method arguments fail.
     *
     * @param ex The exception that contains details about validation errors.
     * @return A ResponseEntity containing a map of field names and error messages, with a BAD_REQUEST status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Create a map to store validation errors with field names as keys and error messages as values.
        Map<String, String> errors = new HashMap<>();

        // Iterate over all errors in the binding result and extract field names and messages
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            // Cast the error to FieldError to extract field-specific information
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            // Add the field name and error message to the map
            errors.put(fieldName, message);
        });

        // Return a BAD_REQUEST response with the error map.
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
