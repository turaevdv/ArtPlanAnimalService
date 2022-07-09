package ru.turaev.animalservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.turaev.animalservice.exception.*;
import ru.turaev.animalservice.exception.exceptionmodel.BaseExceptionModel;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler({AnimalNotFoundException.class, HasNotAnimalException.class, IllegalAnimalException.class, AuthenticationException.class})
    public ResponseEntity<BaseExceptionModel> handleUserNotFoundException(BaseException ex) {
        HttpStatus httpStatus = ex.getStatus();
        BaseExceptionModel exceptionModel = BaseExceptionModel.builder()
                .message(ex.getMessage())
                .status(httpStatus)
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(exceptionModel, httpStatus);
    }
}