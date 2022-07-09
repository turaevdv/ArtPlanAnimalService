package ru.turaev.animalservice.exception;

import org.springframework.http.HttpStatus;

public class IllegalAnimalException extends BaseException {

    public IllegalAnimalException() {
        this("An animal with that name already exists");
    }

    public IllegalAnimalException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
