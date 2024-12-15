package com.rodriquesperry.backend.service;

import com.rodriquesperry.backend.pojo.Animal;
import org.springframework.data.domain.Page;

import java.util.List;

// The AnimalService interface defines the contract for the service layer.
// It declares methods for managing Animal objects without specifying their implementations.
public interface AnimalService {

    // Method to retrieve all Animal objects.
    // Returns a List containing all animals.
    Page<Animal> getAllAnimals(int page, int size);

    // Method to retrieve a single Animal object by its unique ID.
    // Takes a String parameter 'id' representing the animal's ID.
    Animal getAnimalById(String id);

    // Method to save a new Animal object.
    // Takes an Animal parameter representing the object to be saved.
    void saveAnimal(Animal animal);

    // Method to update an existing Animal object by its ID.
    // Takes a String 'id' for the animal's ID and an Animal object with updated details.
    void updateAnimal(String id, Animal animal);

    // Method to delete an Animal object by its ID.
    // Takes a String 'id' representing the ID of the animal to be deleted.
    void deleteAnimal(String id);
}
