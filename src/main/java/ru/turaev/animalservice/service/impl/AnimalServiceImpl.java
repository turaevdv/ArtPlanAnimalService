package ru.turaev.animalservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.turaev.animalservice.entity.Animal;
import ru.turaev.animalservice.exception.AnimalNotFoundException;
import ru.turaev.animalservice.exception.HasNotAnimalException;
import ru.turaev.animalservice.exception.IllegalAnimalException;
import ru.turaev.animalservice.repository.AnimalRepository;
import ru.turaev.animalservice.service.AnimalService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;

    @Override
    public List<Animal> getAnimalsByOwnerId(long ownerId) {
        List<Animal> animals = animalRepository.findAnimalsByOwnerId(ownerId);
        if (animals.isEmpty()) {
            throw new HasNotAnimalException("User with id " + ownerId + " hasn't any animals");
        }
        return animals;
    }

    @Override
    public Animal getAnimalById(long animalId) {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new AnimalNotFoundException("Animal with id " + animalId + " not found"));
    }

    @Override
    public Animal createAnimal(Animal animal) {
        try {
            return animalRepository.save(animal);
        } catch (Exception ex) {
            throw new IllegalAnimalException("An animal named " + animal.getName() + " already exists");
        }
    }

    @Transactional
    @Override
    public Animal editAnimal(long id, Animal animal) {
        Animal existAnimal = getAnimalById(id);
        edit(existAnimal, animal);
        return existAnimal;
    }

    @Transactional
    @Override
    public Animal deleteAnimal(long id) {
        Animal animal = getAnimalById(id);
        animalRepository.delete(animal);
        return animal;
    }

    @Override
    public List<Animal> deleteAnimalsByOwnerId(long ownerId) {
        List<Animal> animals = getAnimalsByOwnerId(ownerId);
        for (Animal animal : animals) {
            deleteAnimal(animal.getId());
        }
        return animals;
    }

    private void edit(Animal oldAnimal, Animal newAnimal) {
        oldAnimal.setAnimalType(newAnimal.getAnimalType());
        oldAnimal.setGender(newAnimal.getGender());
        oldAnimal.setDateOfBirth(newAnimal.getDateOfBirth());
        oldAnimal.setName(newAnimal.getName());
        oldAnimal.setOwnerId(newAnimal.getOwnerId());
    }
}
