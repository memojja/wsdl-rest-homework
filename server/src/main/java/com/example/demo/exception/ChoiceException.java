package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ChoiceException extends RuntimeException {
    public ChoiceException(Long userId) {
        super("could not find user (" + userId + " ).");
    }
}
