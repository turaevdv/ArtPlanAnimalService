package ru.turaev.animalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.turaev.animalservice.dto.UserDto;
import ru.turaev.animalservice.entity.Animal;
import ru.turaev.animalservice.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("animalservice/api/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @GetMapping
    public List<Animal> getAnimalByOwnerId(@RequestParam("ownerId") long ownerId) {
        return animalService.getAnimalsByOwnerId(ownerId);
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable long id) {
        return animalService.getAnimalById(id);
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }

    @PutMapping("/{id}")
    public Animal editAnimal(@PathVariable long id, @RequestBody Animal animal) {
        return animalService.editAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public Animal deleteAnimal(@PathVariable long id) {
        return animalService.deleteAnimal(id);
    }

    @DeleteMapping
    public List<Animal> deleteAnimalsByOwnerId(@RequestBody UserDto userDto) {
        return animalService.deleteAnimalsByOwnerId(userDto.getId());
    }
}
