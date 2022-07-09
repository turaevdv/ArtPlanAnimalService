package ru.turaev.animalservice.exception;

import org.springframework.http.HttpStatus;

public class HasNotAnimalException extends BaseException {
    public HasNotAnimalException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
