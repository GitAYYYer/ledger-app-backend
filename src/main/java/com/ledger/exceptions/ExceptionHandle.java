package com.ledger.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseStatusException resolveResponseStatusException(ResponseStatusException e) {
        return e;
    }

}
