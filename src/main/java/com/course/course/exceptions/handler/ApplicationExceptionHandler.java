package com.course.course.exceptions.handler;

import com.course.course.dtos.responseDto.ApiResponse;
import com.course.course.exceptions.BackendException;
import com.course.course.exceptions.ClientException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    //Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> methodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        exception.getBindingResult().getGlobalErrors().forEach((globalError) -> {
            errors.put(globalError.getObjectName(), globalError.getDefaultMessage());
        });

        LOGGER.error(errors.toString());

        return new ResponseEntity<>(ApiResponse.error("Something wants wrong",errors,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    //Handle Invalid JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> notReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Invalid request body : " + exception.getMessage());
        LOGGER.error(errors.toString());
        return new ResponseEntity<>(ApiResponse.error("Invalid request body",errors,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    //Handle Entity not found errors
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> exception(EntityNotFoundException exception) {
        LOGGER.error("Entity not found: {}", exception.getMessage(), exception);
        Map<String, String> errors = new HashMap<>();
        errors.put("message", "Entity not found : " + exception.getMessage());
        LOGGER.error(errors.toString());
        return new ResponseEntity<>(ApiResponse.error("Entity not found",errors,HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    //Handle Internal server errors
    @ExceptionHandler(BackendException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> backendException(BackendException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        LOGGER.error(errors.toString());
        return new ResponseEntity<>(ApiResponse.error("Internal Server Error",errors,HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Handle Custom Client Exception
    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> clientException(ClientException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", exception.getMessage());
        LOGGER.error(errors.toString());
        return new ResponseEntity<>(ApiResponse.error(exception.getMessage(),errors,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
