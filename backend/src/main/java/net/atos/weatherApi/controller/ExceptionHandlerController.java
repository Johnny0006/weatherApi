package net.atos.weatherApi.controller;

import java.util.concurrent.TimeoutException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(TimeoutException.class)
    public String InvalidArgumentHandler(TimeoutException ex) {
        return "Error: " + ex.getMessage();
    }
}
