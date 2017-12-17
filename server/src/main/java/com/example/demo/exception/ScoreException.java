package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScoreException extends RuntimeException {
    public ScoreException(Long questionId) {
        super("could not found question ( " + questionId +" ).");
    }
}
