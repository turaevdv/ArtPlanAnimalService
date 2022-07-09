package ru.turaev.animalservice.service;

import ru.turaev.animalservice.entity.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> getAnimalsByOwnerId(long ownerId);
    Animal getAnimalById(long animalId);
    Animal createAnimal(Animal animal);
    Animal editAnimal(long id, Animal animal);
    Animal deleteAnimal(long id);
    List<Animal> deleteAnimalsByOwnerId(long ownerId);
}
