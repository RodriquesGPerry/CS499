package com.rockdigital.animalshelter.service;

import com.rockdigital.animalshelter.POJO.Animal;
import com.rockdigital.animalshelter.constants.Constants;
import com.rockdigital.animalshelter.repository.AnimalShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalShelterService {

    AnimalShelterRepository animalShelterRepository;

    public AnimalShelterService(AnimalShelterRepository animalShelterRepository) {
        this.animalShelterRepository =animalShelterRepository;
    }

    public List<Animal> getAnimals() {
        return animalShelterRepository.getAnimals();
    }

    public Animal getAnimal(int index) {
       return animalShelterRepository.getAnimal(index);
    }

    public void addAnimal(Animal animal) {
        animalShelterRepository.addAnimal(animal);
    }

    public void updateAnimal(int index, Animal animal) {
        animalShelterRepository.updateAnimal(index, animal);
    }

    public int getIndexFromId(String id) {
        for (int i = 0; i < animalShelterRepository.getAnimals().size(); i++) {
            if (animalShelterRepository.getAnimal(i).getAnimalId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Animal getAnimalFromId(String id) {
        int index = getIndexFromId(id);
        return index == Constants.NOT_FOUND ? new Animal() : animalShelterRepository.getAnimal(index);
    }

    public String handleSubmit(Animal animal) {
        int index = getIndexFromId(animal.getAnimalId());
        // Handles when the update button is clicked, determines if we are adding or if we are updating.
        if (index == Constants.NOT_FOUND) {
            System.out.println("Adding new animal with ID: " + animal.getAnimalId());
            addAnimal(animal);
        } else {
            updateAnimal(index, animal);
            System.out.println("Updating animal at index: " + index);
        }
        return Constants.SUCCESS_STATUS;
    }
}
