package com.course.course.exceptions.handler;

import com.course.course.dtos.responseDto.ApiResponse;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> methodArgumentException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(ApiResponse.error("Something wants wrong",errors,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> notReadableException(HttpMessageNotReadableException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put(exception.getLocalizedMessage(), exception.getMessage());
        return new ResponseEntity<>(ApiResponse.error("Something wants wrong",errors,HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
