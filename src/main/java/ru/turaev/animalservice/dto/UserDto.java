package ru.turaev.animalservice.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {
    private final long id;

    public UserDto() {
        this.id = 0;
    }
}
