package com.pa.books.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author apo
 */
@ControllerAdvice
public class GeneralErrorAdvisor {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String bookNotFound() {
        return "errors/404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFound() {
        return "errors/404";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public String internalServerError() {
        return "errors/500";
    }
}
