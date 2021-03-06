package com.emreturgutce.bookcase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler({ NotFoundException.class })
    protected ResponseEntity<Map<String, String>> notFound(RuntimeException exception, WebRequest request) {
        Map<String, String> map = generateErrorResponse(exception);

        map.put("code", "404 Not Found");

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ BadRequestException.class })
    protected ResponseEntity<Map<String, String>> badRequest(RuntimeException exception, WebRequest request) {
        Map<String, String> map = generateErrorResponse(exception);

        map.put("code", "400 Bad Request");

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    protected ResponseEntity<Map<String, String>> argumentTypeError(RuntimeException exception) {
        Map<String, String> map = generateErrorResponse(exception);

        map.put("code", "400 Bad Request");

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    private Map<String, String> generateErrorResponse(RuntimeException exception) {
        Map<String, String> map = new HashMap<>();

        map.put("message", exception.getMessage());

        return map;
    }
}
