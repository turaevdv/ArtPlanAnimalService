package ru.turaev.animalservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AnimalNotFoundException extends BaseException {

    public AnimalNotFoundException() {
        this("Animal not found");
    }

    public AnimalNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
