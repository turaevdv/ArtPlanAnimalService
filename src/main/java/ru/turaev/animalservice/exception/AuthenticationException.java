package ru.turaev.animalservice.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends BaseException {

    public AuthenticationException(String s) {
        super(s, HttpStatus.UNAUTHORIZED);
    }

    public AuthenticationException() {
        this("Unauthorized");
    }
}
