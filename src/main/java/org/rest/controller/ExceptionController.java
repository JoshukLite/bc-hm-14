package org.rest.controller;

import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Problem> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Problem.create()
                        .withTitle("Not found")
                        .withDetail(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Problem> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Problem.create()
                        .withTitle("Unexpected exception")
                        .withDetail("Internal server error"));
    }
}
